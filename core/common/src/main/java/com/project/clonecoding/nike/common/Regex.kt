package com.project.clonecoding.nike.common

import android.util.Patterns
import java.util.regex.Pattern

object Regex {
    const val AUTH_CODE_MAX_LENGTH = 6
    const val PW_MIN_LENGTH = 8
    const val PW_MAX_LENGTH = 16
    const val NICKNAME_MIN_LENGTH = 2
    const val NICKNAME_MAX_LENGTH = 10

    private const val EMAIL_PROGRESS_REGEX = "^[a-zA-Z0-9._%+-/=?^{}~!#$&'*|@-]*$"
    private const val PW_PROGRESS_REGEX = """^[0-9a-zA-Z!@#$%^+\-=]*$"""
    private const val PW_REGEX =  "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{${PW_MIN_LENGTH},${PW_MAX_LENGTH}}$"
    private const val NICKNAME_REGEX = "^(?=.*[가-힣]|(?=.*[A-Za-z]{2,}))[A-Za-z0-9가-힣]{${NICKNAME_MIN_LENGTH},${NICKNAME_MAX_LENGTH}}$"
    private const val NICKNAME_PROGRESS_REGEX = "^[가-힣A-Za-z0-9]*$"

    fun checkEmailRegex(email: String): Boolean =
        if(email.isBlank()) false else Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun checkEmailProgressRegex(email: String): Boolean =
        Pattern.matches(EMAIL_PROGRESS_REGEX, email)

    fun checkPasswordRegex(password: String): Boolean =
        if(password.isBlank()) false else Pattern.matches(PW_REGEX, password)

    fun checkPasswordProgressRegex(password: String): Boolean =
        Pattern.matches(PW_PROGRESS_REGEX, password)

    fun checkNicknameRegex(nickname: String):Boolean =
        if(nickname.isBlank()) false else Pattern.matches(NICKNAME_REGEX, nickname)

    fun checkNicknameProgressRegex(nickname: String):Boolean =
        Pattern.matches(NICKNAME_PROGRESS_REGEX, nickname)
}