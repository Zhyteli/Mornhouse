package com.test.mornhouse.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mornhouse.data.repository.NumberFactRepositoryImpl
import com.test.mornhouse.domain.repository.NumberFactRepository
import com.test.mornhouse.domain.usecase.GetAllFactsUseCase
import com.test.mornhouse.domain.usecase.GetFactUseCase
import com.test.mornhouse.domain.usecase.GetRandomFactUseCase
import com.test.mornhouse.presentation.mapper.toUi
import com.test.mornhouse.presentation.model.NumberFactUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFactUseCase: GetFactUseCase,
    private val getRandomFactUseCase: GetRandomFactUseCase,
    private val getAllFactsUseCase: GetAllFactsUseCase
) : ViewModel() {

    private val _numberFact = MutableStateFlow<String?>(null)
    val numberFact: StateFlow<String?> = _numberFact

    val factsHistory: StateFlow<List<NumberFactUI>> = getAllFactsUseCase()
        .map { list ->
            list.map {
                it.toUi()
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val _selectedFact = MutableStateFlow<NumberFactUI?>(null)
    val selectedFact: StateFlow<NumberFactUI?> = _selectedFact

    fun getFact(number: String) {
        val error = CoroutineExceptionHandler { _, _ ->
            _numberFact.value = "Error fetching fact"
        }
        viewModelScope.launch(error) {
            val fact = getFactUseCase(number)
            _numberFact.value = fact
        }
    }

    fun getRandomFact() {
        val error = CoroutineExceptionHandler { _, _ ->
            _numberFact.value = "Error fetching random fact"
        }
        viewModelScope.launch(error) {
            val fact = getRandomFactUseCase()
            _numberFact.value = fact
        }
    }

    fun selectFact(fact: NumberFactUI) {
        _selectedFact.value = fact
    }
}
