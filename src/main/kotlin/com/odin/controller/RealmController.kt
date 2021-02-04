package com.odin.controller

import com.odin.model.Realm
import com.odin.repository.RealmRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import java.net.URI

@Controller(value = "/realm")
class RealmController(private val realmRepository: RealmRepository) {

    @Get
    fun getRealms(): HttpResponse<List<Realm>> {
        return HttpResponse.ok(realmRepository.findAll().toList())
    }

    @Get("/{realmCode}")
    fun getRealm(@PathVariable realmCode: String): HttpResponse<Realm> {
        return realmRepository.findById(realmCode)
            .map { realm -> HttpResponse.ok(realm) }
            .orElse(HttpResponse.notFound())
    }

    @Post
    fun createRealm(@Body realm: Realm): HttpResponse<Realm>? {
        var save = realmRepository.save(realm)
        return HttpResponse.created(realm, URI.create("/"+save.realmCode))
    }
}