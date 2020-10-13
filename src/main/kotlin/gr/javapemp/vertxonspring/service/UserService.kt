package gr.javapemp.vertxonspring.service

import gr.javapemp.vertxonspring.model.User
import gr.javapemp.vertxonspring.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional
    fun save(user: User): User = userRepository.save(user)

    fun findAll(): List<User> = userRepository.findAll().toList()

    fun findAll(pageable: Pageable): Page<User> = userRepository.findAll(pageable)

    fun findById(id: Long): Optional<User> = userRepository.findById(id)
}
