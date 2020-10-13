package gr.javapemp.vertxonspring.vertx.factory

import io.vertx.core.Verticle
import io.vertx.core.spi.VerticleFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class SpringVerticleFactory : VerticleFactory, ApplicationContextAware {

    private var applicationContext: ApplicationContext? = null

    override fun prefix(): String = "spring-vertx"

    override fun blockingCreate(): Boolean = true

    override fun createVerticle(verticleName: String, classLoader: ClassLoader): Verticle {
        // Our convention in this example is to give the class name as verticle name
        val clazz = VerticleFactory.removePrefix(verticleName)
        return applicationContext!!.getBean(Class.forName(clazz)) as Verticle
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

}
