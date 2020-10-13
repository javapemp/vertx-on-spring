package gr.javapemp.vertxonspring.vertx.verticle

import gr.javapemp.vertxonspring.service.BookAsyncService
import gr.javapemp.vertxonspring.service.UserAsyncService
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.serviceproxy.ServiceBinder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class VertxWorker(
    private val bookAsyncService: BookAsyncService,
    private val userAsyncService: UserAsyncService
) : AbstractVerticle() {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun start(startPromise: Promise<Void>) {
        ServiceBinder(vertx).setAddress(BookAsyncService.ADDRESS).register(BookAsyncService::class.java, bookAsyncService).completionHandler { ar ->
            if (ar.succeeded()) {
                log.info("BookAsyncVerticle started")
            } else {
                startPromise.fail(ar.cause())
            }
        }

        ServiceBinder(vertx).setAddress(UserAsyncService.ADDRESS).register(UserAsyncService::class.java, userAsyncService).completionHandler { ar ->
            if (ar.succeeded()) {
                log.info("UserAsyncVerticle started")
            } else {
                startPromise.fail(ar.cause())
            }
        }

        startPromise.complete()
    }

}
