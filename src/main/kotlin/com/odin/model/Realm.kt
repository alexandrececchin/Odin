package com.odin.model

import io.micronaut.data.annotation.DateCreated
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "realm")
data class Realm (@Id val realCode: String, @DateCreated val created: Date)