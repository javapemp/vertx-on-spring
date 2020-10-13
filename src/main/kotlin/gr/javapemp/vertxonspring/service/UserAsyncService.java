package gr.javapemp.vertxonspring.service;

import gr.javapemp.vertxonspring.model.User;
import gr.javapemp.vertxonspring.model.pagination.PageList;
import gr.javapemp.vertxonspring.model.pagination.Pagination;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.util.List;

@ProxyGen
public interface UserAsyncService {

    String ADDRESS = UserAsyncService.class.getName();

    void add(JsonObject jsonObject, Handler<AsyncResult<User>> resultHandler);

    void findAll(Handler<AsyncResult<List<User>>> resultHandler);

    // Overloaded methods not allowed in ProxyGen
    void findAllPageable(Pagination pagination, Handler<AsyncResult<PageList>> resultHandler);

    void findById(Long id, Handler<AsyncResult<User>> resultHandler);

}
