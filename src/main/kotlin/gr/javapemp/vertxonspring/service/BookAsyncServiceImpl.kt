package gr.javapemp.vertxonspring.service

import gr.javapemp.vertxonspring.model.Book
import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import org.springframework.stereotype.Component

@Component
class BookAsyncServiceImpl(
    private val bookService: BookService
) : BookAsyncService {

    override fun add(book: Book, resultHandler: Handler<AsyncResult<Book>>) {
        val saved = bookService.save(book)
        resultHandler.handle(Future.succeededFuture(saved))
    }

    override fun findAll(resultHandler: Handler<AsyncResult<List<Book>>>) {
        val all: List<Book> = bookService.findAll()
        resultHandler.handle(Future.succeededFuture(all))
    }

}
