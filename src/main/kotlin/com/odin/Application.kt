package com.odin

import com.odin.model.Realm
import com.odin.repository.RealmRepository
import io.micronaut.context.ApplicationContextLifeCycle
import io.micronaut.context.annotation.Configuration
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.data.annotation.RepositoryConfiguration
import io.micronaut.runtime.Micronaut.*
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import io.micronaut.scheduling.annotation.Async
import io.reactivex.Flowable
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@OpenAPIDefinition(
    info = Info(
            title = "Odin",
            version = "0.0"
    )
)
object Api {
}
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.odin")
		.start()
}


@Singleton
class DataLoader : ApplicationEventListener<ServerStartupEvent>{

    @Inject
    lateinit var repository: RealmRepository

    private fun save(realm: Realm) {
        println("Saving realm $realm")
        repository.save(realm)
    }

    override fun onApplicationEvent(event: ServerStartupEvent?) {
        Flowable.just("TENOR", "SERVICE_EVENTS")
            .map { Realm(it, Calendar.getInstance().time) }
            .forEach(this::save);
    }

}