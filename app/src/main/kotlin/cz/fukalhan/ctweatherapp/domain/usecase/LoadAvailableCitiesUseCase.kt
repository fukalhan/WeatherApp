package cz.fukalhan.ctweatherapp.domain.usecase

import cz.fukalhan.ctweatherapp.domain.repository.CityRepository

class LoadAvailableCitiesUseCase(
    private val cityRepository: CityRepository,
) {
    suspend operator fun invoke(): List<String> {
        return cityRepository.getAvailableCities()
    }
}