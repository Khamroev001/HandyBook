package khamroev.handybook.model

data class UserToken(
    val access_token: String,
    val id: Int,
    val username: String
)