package com.viniciussantos.SpringRestAPI.interfaces

import com.viniciussantos.SpringRestAPI.domain.DriverRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.MediaType


@Service // This annotation is used to indicate that the class is a service - indica que a classe é um serviço
@RestController // This annotation is used to indicate that the class is a controller - indica que a classe é um controlador
@RequestMapping(produces=[MediaType.APPLICATION_JSON_VALUE])// This annotation is used to indicate that the class is a controller - indica que a classe é um controlador
class DriverAPI(val driverRepository: DriverRepository) {

    @GetMapping("/drivers") // This annotation is used to indicate that the function is a GET request - indica que a função é uma solicitação GET
    fun listDrivers() = driverRepository.findAll() // This function returns a list of drivers - essa função retorna uma lista de motoristas


}