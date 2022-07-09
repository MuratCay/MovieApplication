package com.hardcoder.movieapp.utils

object StringUtils {
    fun checkEmailValidation(emailText: String): Validation {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return if (emailText.matches(emailPattern.toRegex())) {
            Validation.VALID
        } else {
            Validation.INVALID
        }
    }
}

enum class Validation {
    VALID,
    INVALID
}