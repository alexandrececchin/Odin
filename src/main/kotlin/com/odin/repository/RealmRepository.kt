package com.odin.repository

import com.odin.model.Realm
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface RealmRepository :CrudRepository<Realm, String> {

}