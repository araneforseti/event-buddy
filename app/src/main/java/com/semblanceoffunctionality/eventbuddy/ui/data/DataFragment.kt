package com.semblanceoffunctionality.eventbuddy.ui.data

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.provider.DocumentsContract
import android.net.Uri
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast.LENGTH_SHORT
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.semblanceoffunctionality.eventbuddy.R
import com.semblanceoffunctionality.eventbuddy.databinding.FragmentDataBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.BufferedReader

@AndroidEntryPoint
class DataFragment : Fragment() {
    private val dataViewModel: DataViewModel by viewModels()
    private lateinit var chooseFileForExportLauncher: ActivityResultLauncher<Intent?>
    private lateinit var openFileResultLauncher: ActivityResultLauncher<Intent?>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentDataBinding>(
            inflater,
            R.layout.fragment_data,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            importData.setOnClickListener {
                chooseFileForImport()

                Snackbar.make(requireView(), R.string.import_data, LENGTH_SHORT).show()
            }
            exportData.setOnClickListener {
                chooseFileForExport()

                Snackbar.make(requireView(), R.string.export_data, LENGTH_SHORT).show()
            }
        }

        chooseFileForExportLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val jsonContent = dataViewModel.exportData()
                val chosenUri = result.data?.data!!
                val outputStream = context?.contentResolver?.openOutputStream(chosenUri)
                outputStream?.write(jsonContent?.toByteArray())
                outputStream?.close()
            }
        }

        openFileResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val chosenUri = result.data?.data!!
                val inputStream = context?.contentResolver?.openInputStream(chosenUri)
                val content = inputStream?.bufferedReader()?.use(BufferedReader::readText)
                dataViewModel.importData(content!!)
            }
        }
        return binding.root
    }

    private fun chooseFileForExport() {
        val relativePath = "Documents/"
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/json"
            putExtra(Intent.EXTRA_TITLE, "eventBuddyBackup.json")

            // Optionally, specify a URI for the directory that should be opened in
            // the system file picker before your app creates the document.
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, Uri.parse(relativePath))
        }

        chooseFileForExportLauncher.launch(intent)
    }

    private fun chooseFileForImport() {
        val relativePath = "Documents/"

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/json"

            // Optionally, specify a URI for the file that should appear in the
            // system file picker when it loads.
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, Uri.parse(relativePath))
        }

        openFileResultLauncher.launch(intent)
    }
}
