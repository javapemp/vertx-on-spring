package gr.javapemp.vertxonspring.dto

import gr.javapemp.vertxonspring.model.User
import javax.validation.constraints.Email

data class CreateUserCommand(
    val authGuid: String,

    val username: String,

    @Email
    val email: String,

    val timezone: String,

    val lang: String,

    val firstName: String,

    val lastName: String,

    val enabled: Boolean
) {
    fun toUser(): User {
        val user = User()
        user.authGuid = authGuid
        user.username = username
        user.email = email
        user.timezone = timezone
        user.lang = lang
        user.firstName = firstName
        user.lastName = lastName
        user.isEnabled = enabled
        return user
    }
}
