package gr.javapemp.vertxonspring.service

import com.fasterxml.jackson.databind.ObjectMapper
import gr.javapemp.vertxonspring.dto.CreateUserCommand
import gr.javapemp.vertxonspring.model.User
import gr.javapemp.vertxonspring.model.pagination.PageList
import gr.javapemp.vertxonspring.model.pagination.Pagination
import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.json.JsonObject
import io.vertx.serviceproxy.ServiceException
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component


@Component
class UserAsyncServiceImpl(
    private val userService: UserService,
    private val objectMapper: ObjectMapper
) : UserAsyncService {

    override fun add(jsonObject: JsonObject, resultHandler: Handler<AsyncResult<User>>) {
        val command = objectMapper.readValue(jsonObject.toString(), CreateUserCommand::class.java)
        val saved = userService.save(command.toUser())
        resultHandler.handle(Future.succeededFuture(saved))
    }

    override fun findAll(resultHandler: Handler<AsyncResult<List<User>>>) {
        val all: List<User> = userService.findAll()
        resultHandler.handle(Future.succeededFuture(all))
    }

    override fun findAllPageable(pagination: Pagination, resultHandler: Handler<AsyncResult<PageList>>) {
        val all: Page<User> = userService.findAll(pagination.toPageable())
        resultHandler.handle(Future.succeededFuture(PageList(all)))
    }

    override fun findById(id: Long, resultHandler: Handler<AsyncResult<User>>) {
        userService.findById(id).ifPresent {
            resultHandler.handle(Future.succeededFuture(it))
        }
        resultHandler.handle(ServiceException.fail(404, "Entity not found"))
    }

}
