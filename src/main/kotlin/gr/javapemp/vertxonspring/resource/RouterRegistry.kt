package gr.javapemp.vertxonspring.resource

import gr.javapemp.vertxonspring.model.pagination.Pagination
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.serviceproxy.ServiceException
import javax.validation.ValidationException

interface RouterRegistry {

    fun initRouter(): Router

    fun failureHandler(ctx: RoutingContext) {
        val failure = ctx.failure()
        if (failure is ServiceException) {
            ctx.response().setStatusCode(failure.failureCode()).end(failure.message)
        }
        if (failure is ValidationException) {
            ctx.response().setStatusCode(400).end(failure.message)
        }
    }

    fun resultHandler(ctx: RoutingContext, statusCode: Int): Handler<AsyncResult<Any>> {
        return Handler {
            if (it.succeeded()) {
                val body = it.result()
                val bodyAsJson = Json.encode(body)
                ctx.response()
                    .setStatusCode(statusCode)
                    .end(bodyAsJson)
            } else {
                ctx.fail(it.cause())
            }
        }
    }

    fun pageHandler(ctx: RoutingContext) {
        val pageParams = ctx.queryParam("page")
        val sizeParams = ctx.queryParam("size")

        val page = if (pageParams.isNullOrEmpty()) 0 else pageParams[0].toInt()
        val size = if (sizeParams.isNullOrEmpty()) 10 else sizeParams[0].toInt()

        ctx.put("pagination", Pagination(page, size)).next()
    }

}
