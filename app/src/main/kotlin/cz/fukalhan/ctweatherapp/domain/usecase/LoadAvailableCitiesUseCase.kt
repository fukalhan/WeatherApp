package cz.fukalhan.ctweatherapp.domain.usecase

import cz.fukalhan.ctweatherapp.domain.repository.CityRepository

/**
 * Use case for loading available cities.
 *
 * This use case retrieves a list of city names that are available in the application.
 *
 * @property cityRepository Repository for managing city data.
 */
class LoadAvailableCitiesUseCase(
    private val cityRepository: CityRepository,
) {
    suspend operator fun invoke(): List<String> {
        return cityRepository.getAvailableCities()
    }
}