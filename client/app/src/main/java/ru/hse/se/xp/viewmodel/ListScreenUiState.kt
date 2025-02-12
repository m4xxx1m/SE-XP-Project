package ru.hse.se.xp.viewmodel

import ru.hse.se.xp.model.ListItem

data class ListScreenUiState(
    val listItems: List<ListItem>? = null,
    val status: Boolean = false
)