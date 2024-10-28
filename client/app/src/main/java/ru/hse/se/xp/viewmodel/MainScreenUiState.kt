package ru.hse.se.xp.viewmodel

import ru.hse.se.xp.model.UserList

data class MainScreenUiState(
    val lists: List<UserList>? = null,
    val status: Boolean = false
)
