package gr.javapemp.vertxonspring.repository

import gr.javapemp.vertxonspring.model.Book
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Long>
