package com.viniciussantos.SpringRestAPI.interfaces.incoming


import TravelRequestMapper
import com.viniciussantos.SpringRestAPI.domain.TravelRequestStatus
import com.viniciussantos.SpringRestAPI.domain.TravelService
import org.springframework.hateoas.EntityModel
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.Inet4Address
import java.time.LocalDateTime


@Service
@RestController
@RequestMapping(path = ["/travelRequests"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TravelRequestAPI(val travelService: TravelService, val mapper: TravelRequestMapper) {
    @PostMapping
    fun makeTravelRequest(@RequestBody travelRequestInput: TravelRequestInput): EntityModel<TravelRequestOutput> {
        val travelRequest = travelService.saveTravelRequest(mapper.map(travelRequestInput))
        val output = mapper.map(travelRequest)
        return mapper.buildOutputModel(travelRequest, output)
    }
    @GetMapping("/nearby")
    fun listNearbyRequests(@RequestParam currentAddress: String):List<EntityModel<TravelRequestOutput>>{
        val requests = travelService.listNearByTravelRequests(currentAddress)
        return emptyList()
    }
}

data class TravelRequestInput(
    val passengerId: Long,
    val origin: String,
    val destination: String
)

data class TravelRequestOutput(
    val id: Long,
    val origin: String,
    val destination: String,
    val status: TravelRequestStatus,
    val creationDate: LocalDateTime
)