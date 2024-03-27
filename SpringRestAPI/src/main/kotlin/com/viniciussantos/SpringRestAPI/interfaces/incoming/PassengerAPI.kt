package com.viniciussantos.SpringRestAPI.interfaces.incoming

import com.viniciussantos.SpringRestAPI.domain.Passenger
import com.viniciussantos.SpringRestAPI.domain.PassengerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@Service // This annotation is used to indicate that the class is a service - indica que a classe é um serviço
@RestController // This annotation is used to indicate that the class is a controller - indica que a classe é um controlador
@RequestMapping(
    path = ["/passengers"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)// This annotation is used to indicate that the class is a controller - indica que a classe é um controlador
class PassengerAPI(val passengerRepository: PassengerRepository) {

    @GetMapping // This annotation is used to indicate that the function is a GET request - indica que a função é uma solicitação GET
    fun listPassengers() =
        passengerRepository.findAll() // This function returns a list of passengers - essa função retorna uma lista de passageiros

    @GetMapping("/{id}") // This annotation is used to indicate that the function is a GET request - indica que a função é uma solicitação GET
    fun findPassenger(@PathVariable("id") id: Long) =
        passengerRepository
            .findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }// This function returns a passenger by id - essa função retorna um passageiro por id

    @PostMapping
    fun createPassenger(@RequestBody passenger: Passenger): Passenger =
        passengerRepository.save(passenger) // This function creates a passenger - essa função cria um passageiro

    @PutMapping("/{id}")
    fun fullUpdatePassenger(@PathVariable("id") id: Long, @RequestBody passenger: Passenger): Passenger {
        val newPassenger = findPassenger(id).copy(
            name = passenger.name
        )
        return passengerRepository.save(newPassenger)

    }

    @PatchMapping("/{id}")
    fun incrementalUpdatePassenger(@PathVariable("id") id: Long, @RequestBody passenger: PatchPassenger): Passenger {
        val foundPassenger = findPassenger(id)
        val copyPassenger = foundPassenger.copy(
            name = passenger.name ?: foundPassenger.name
        )
        return passengerRepository.save(copyPassenger)
    }

    @DeleteMapping("/{id}")
    fun deletePassenger(@PathVariable("id") id: Long) = passengerRepository.deleteById(id) // This function deletes a passenger by id - essa função deleta um passageiro por id


}

data class PatchPassenger(
    val name: String?
)