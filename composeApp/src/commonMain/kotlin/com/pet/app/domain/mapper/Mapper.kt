package com.pet.app.domain.mapper

interface Mapper<Domain, Entity> {
    fun mapToDomain(entity: Entity): Domain
}