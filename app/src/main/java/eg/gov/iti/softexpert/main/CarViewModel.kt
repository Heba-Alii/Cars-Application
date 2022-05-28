package eg.gov.iti.softexpert.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eg.gov.iti.softexpert.business.entities.CarResponse
import eg.gov.iti.softexpert.business.entities.DataState
import eg.gov.iti.softexpert.business.useCase.abtraction.CarUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CarViewModel(private val carUseCase: CarUseCase) : ViewModel() {
    private val _carDataState: MutableLiveData<DataState<CarResponse>> = MutableLiveData()
    val carDataState: LiveData<DataState<CarResponse>>
        get() = _carDataState
    val pageNumber = 1

    init {
        getCars()
    }

    fun getCars() {

        viewModelScope.launch {

            carUseCase.getCars(pageNumber)
                .onStart { _carDataState.value = DataState.Loading(true) }
                .catch {
                    _carDataState.value = DataState.Loading(false)
                    _carDataState.value = DataState.Failure(it)
                }.collect {
                    _carDataState.value = DataState.Loading(false)
                    _carDataState.value = DataState.Success(it)
                }

        }
    }

}