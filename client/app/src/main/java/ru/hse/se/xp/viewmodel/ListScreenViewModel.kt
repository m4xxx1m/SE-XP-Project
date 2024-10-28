package ru.hse.se.xp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.hse.se.xp.model.ListItem

class ListScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ListScreenUiState())
    val uiState: StateFlow<ListScreenUiState> = _uiState.asStateFlow()

    fun loadList() {
        val list: List<ListItem> = listOf(
            ListItem(0, 0, 0, "Матстат", true),
            ListItem(0, 0, 0, "SE", false),
            ListItem(0, 0, 0, "АКОС", false),
            ListItem(0, 0, 0, "МЛ", true),
            ListItem(0, 0, 0, "Компиляторы", false)
        )
        _uiState.update { oldValue ->
            oldValue.copy(
                listItems = list,
                status = true
            )
        }
    }

}