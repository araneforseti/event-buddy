package com.semblanceoffunctionality.eventbuddy.ui.personal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.semblanceoffunctionality.eventbuddy.data.Personal
import com.semblanceoffunctionality.eventbuddy.data.PersonalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val personalRepository: PersonalRepository
) : ViewModel() {
    val info = personalRepository.getInfo().asLiveData()

    fun updateInfo(info: Personal) {
        CoroutineScope(Dispatchers.IO).launch {
            personalRepository.updateInfo(info)
        }
    }
}