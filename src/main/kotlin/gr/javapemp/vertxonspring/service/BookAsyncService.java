package gr.javapemp.vertxonspring.service;

import gr.javapemp.vertxonspring.model.Book;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;

@ProxyGen
public interface BookAsyncService {

    String ADDRESS = BookAsyncService.class.getName();

    void add(Book book, Handler<AsyncResult<Book>> resultHandler);

    void findAll(Handler<AsyncResult<List<Book>>> resultHandler);
}
