package com.example.weather.feature.weather.data.remote

import com.example.weather.feature.weather.data.remote.dto.CityDto
import com.example.weather.feature.weather.domain.model.City

class CityMapper {
    fun map(dto: CityDto): City = City(
        id = dto.id,
        name = dto.name,
        country = dto.country
    )

    fun mapList(dtos: List<CityDto>): List<City> = dtos.map { map(it) }
}
