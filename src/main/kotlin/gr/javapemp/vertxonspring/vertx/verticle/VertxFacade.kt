package gr.javapemp.vertxonspring.vertx.verticle

import gr.javapemp.vertxonspring.resource.UserRouter
import gr.javapemp.vertxonspring.service.BookAsyncService
import gr.javapemp.vertxonspring.service.UserAsyncService
import io.vertx.core.AbstractVerticle
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import io.vertx.core.Promise
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.Router
import io.vertx.serviceproxy.ServiceProxyBuilder
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class VertxFacade : AbstractVerticle() {
    private val log = LoggerFactory.getLogger(VertxFacade::class.java)

    private lateinit var bookAsyncService: BookAsyncService
    private lateinit var userAsyncService: UserAsyncService

    override fun start(startPromise: Promise<Void>) {
        bookAsyncService = ServiceProxyBuilder(vertx).setAddress(BookAsyncService.ADDRESS).build(BookAsyncService::class.java)
        userAsyncService = ServiceProxyBuilder(vertx).setAddress(UserAsyncService.ADDRESS).build(UserAsyncService::class.java)

        startServer(next = Handler {
            completeStartup(it, startPromise)
        })
    }

    private fun startServer(next: Handler<AsyncResult<HttpServer>>) {
        val apiRouter = Router.router(vertx)

        apiRouter.mountSubRouter("/users", UserRouter(vertx, userAsyncService).initRouter())

        vertx.createHttpServer(HttpServerOptions().also {
            it.isCompressionSupported = true
        })
            .requestHandler(apiRouter)
            .listen(8080, next::handle)
    }

    private fun completeStartup(http: AsyncResult<HttpServer>, promise: Promise<Void>) {
        if (http.succeeded()) {
            log.info("HTTP server started on port: ${http.result().actualPort()}")
            promise.complete()
        } else {
            promise.fail(http.cause())
        }
    }

}
