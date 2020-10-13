package gr.javapemp.vertxonspring.service

import gr.javapemp.vertxonspring.model.Book
import gr.javapemp.vertxonspring.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookService(
    private val bookRepository: BookRepository
) {
    fun save(book: Book): Book = bookRepository.save(book)

    fun findAll(): List<Book> = bookRepository.findAll().toList()
}
