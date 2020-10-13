package gr.javapemp.vertxonspring.resource

import gr.javapemp.vertxonspring.resource.handler.UserHandler
import gr.javapemp.vertxonspring.service.UserAsyncService
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler

class UserRouter(
    private val vertx: Vertx,
    private val userAsyncService: UserAsyncService
) : RouterRegistry {

    override fun initRouter(): Router {
        val userHandler = UserHandler(userAsyncService)
        val router = Router.router(vertx)

        router.route().handler(BodyHandler.create()).failureHandler(this::failureHandler)

        router.get("/")
            .handler(this::pageHandler)
            .handler(userHandler::findAll)
        router.get("/:id")
            .handler(userHandler::findById)
        router.post("/")
            .handler(userHandler::createUser)

        return router
    }

}
