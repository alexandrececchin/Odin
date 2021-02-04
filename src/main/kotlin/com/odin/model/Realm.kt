package com.odin.model

import com.fasterxml.jackson.annotation.JsonFormat
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "realm")
data class Realm(@Id @Column(name = "realmCode") val realmCode: String, @Column(name = "description") val description: String? = "") {
    @Column(name = "create_at")
    @DateCreated
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: Date? = Date.from(Instant.now())

    @Column(name = "update_at")
    @DateUpdated
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var updateAt: Date? = Date.from(Instant.now())
}