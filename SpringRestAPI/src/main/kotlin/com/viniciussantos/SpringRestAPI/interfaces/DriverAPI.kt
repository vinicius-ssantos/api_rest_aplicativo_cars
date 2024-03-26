package com.viniciussantos.SpringRestAPI.interfaces

import com.viniciussantos.SpringRestAPI.domain.entity.Driver
import com.viniciussantos.SpringRestAPI.domain.repository.DriverRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate


@Service // This annotation is used to indicate that the class is a service - indica que a classe é um serviço
@RestController // This annotation is used to indicate that the class is a controller - indica que a classe é um controlador
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])// This annotation is used to indicate that the class is a controller - indica que a classe é um controlador
class DriverAPI(val driverRepository: DriverRepository) {

    @GetMapping("/drivers") // This annotation is used to indicate that the function is a GET request - indica que a função é uma solicitação GET
    fun listDrivers() =
        driverRepository.findAll() // This function returns a list of drivers - essa função retorna uma lista de motoristas

    @GetMapping("/drivers/{id}") // This annotation is used to indicate that the function is a GET request - indica que a função é uma solicitação GET
    fun findDriver(@PathVariable("id") id: Long) =
        driverRepository
            .findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }// This function returns a driver by id - essa função retorna um motorista por id

    @PostMapping("/drivers")
    fun createDriver(@RequestBody driver: Driver): Driver =
        driverRepository.save(driver) // This function creates a driver - essa função cria um motorista

    @PutMapping("/drivers/{id}")
    fun fullUpdateDriver(@PathVariable("id") id: Long, @RequestBody driver: Driver): Driver {
        val foundDriver = findDriver(id)
        val copyDriver = foundDriver.copy(
            birthDate = driver.birthDate,
            name = driver.name
        )
        return driverRepository.save(copyDriver)
    }

    @PatchMapping("/drivers/{id}")
    fun incrementalUpdateDriver(@PathVariable("id") id: Long, @RequestBody driver: PatchDriver): Driver {
        val foundDriver = findDriver(id)
        val copyDriver = foundDriver.copy(
            birthDate = driver.birthDate ?: foundDriver.birthDate,
            name = driver.name ?: foundDriver.name
        )
        return driverRepository.save(copyDriver)
    }

    @DeleteMapping("/drivers/{id}")
    fun deleteDriver(@PathVariable("id") id: Long) =
        driverRepository.deleteById(id) // This function deletes a driver by id - essa função deleta um motorista por id

}

data class PatchDriver(
    val name: String?,
    val birthDate: LocalDate?
)