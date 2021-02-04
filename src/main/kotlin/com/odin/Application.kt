package com.odin

import com.odin.model.Realm
import com.odin.repository.RealmRepository
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.Micronaut.*
import io.micronaut.runtime.server.event.ServerStartupEvent
import io.reactivex.Flowable
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
class DataLoader : ApplicationEventListener<ServerStartupEvent> {

    @Inject
    lateinit var repository: RealmRepository
    private var log: Logger = LoggerFactory.getLogger("DataLoader")

    private fun save(realm: Realm) {
        var save = repository.save(realm)
        log.info(" Saved realm ={}", realm);

    }

    override fun onApplicationEvent(event: ServerStartupEvent?) {
        Flowable.just("TENOR", "SERVICE_EVENTS","SPMD", "ECO_FESS")
            .map { Realm(it) }
            .forEach(this::save);
    }
}