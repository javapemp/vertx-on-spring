package gr.javapemp.vertxonspring.resource.handler

import gr.javapemp.vertxonspring.model.pagination.Pagination
import gr.javapemp.vertxonspring.service.UserAsyncService
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component


@Component
class UserHandler(
    private val userAsyncService: UserAsyncService
) {

    fun findAll(ctx: RoutingContext) {
        userAsyncService.findAllPageable(ctx.get<Pagination>("pagination")) {
            if (it.succeeded()) {
                ctx.response().end(it.result().toJson().encodePrettily())
            } else {
                ctx.fail(it.cause())
            }
        }
    }

    fun findById(ctx: RoutingContext) {
        userAsyncService.findById(ctx.pathParam("id").toLong()) {
            if (it.succeeded()) {
                ctx.response().end(it.result().toJson().encodePrettily())
            } else {
                ctx.fail(it.cause())
            }
        }
    }

    fun createUser(ctx: RoutingContext) {
        userAsyncService.add(ctx.bodyAsJson) {
            if (it.succeeded()) {
                ctx.response().end(it.result().toJson().encodePrettily())
            } else {
                ctx.fail(it.cause())
            }
        }
    }

}
