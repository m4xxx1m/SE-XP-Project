package ru.hse.se.xp.model

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val email: String
)

object CurrentUser {
    val userId: Int? = null
}
