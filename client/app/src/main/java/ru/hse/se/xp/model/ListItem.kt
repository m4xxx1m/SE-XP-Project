package ru.hse.se.xp.model

data class ListItem(
    val id: Int,
    val list_id: Int,
    val parent_id: Int,
    val content: String,
    val completed: Boolean
)
