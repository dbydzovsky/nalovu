package cz.dbydzovsky.nalovu.data

import org.jetbrains.annotations.NotNull


data class UserDto (
    @NotNull
    val username: String = "",

    @NotNull
    val password: String = ""
)