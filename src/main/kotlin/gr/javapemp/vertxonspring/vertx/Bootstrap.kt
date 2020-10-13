package gr.javapemp.vertxonspring.vertx

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import gr.javapemp.vertxonspring.vertx.factory.SpringVerticleFactory
import gr.javapemp.vertxonspring.vertx.verticle.VertxFacade
import gr.javapemp.vertxonspring.vertx.verticle.VertxWorker
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@Configuration
class Bootstrap(
    private val verticleFactory: SpringVerticleFactory
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    private val workers: List<String> = listOf(
        VertxWorker::class.java.name
    )
    private val vertxOptions: VertxOptions = VertxOptions()
        .setWorkerPoolSize(20)
    private val deployOptionsRouter = DeploymentOptions()
        .setInstances(5)
    private val deployOptionsWorker = DeploymentOptions()
        .setWorker(true)
        .setInstances(10)

    @EventListener
    fun startupListener(event: ApplicationReadyEvent) {

        val vertx = Vertx.vertx(vertxOptions).also {
            it.registerVerticleFactory(verticleFactory)
        }

        deployVerticle(
            vertx,
            "${verticleFactory.prefix()}:${VertxFacade::class.java.name}",
            deployOptionsRouter
        )

        workers.forEach {
            deployVerticle(
                vertx,
                "${verticleFactory.prefix()}:$it",
                deployOptionsWorker
            )
        }

    }

    fun deployVerticle(vertx: Vertx, verticle: String, deploymentOptions: DeploymentOptions) {
        vertx.deployVerticle(verticle, deploymentOptions) {
            if (it.succeeded()) {
                log.info("Verticle deployed: $verticle")
            }
        }
    }

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper().registerKotlinModule()
}
