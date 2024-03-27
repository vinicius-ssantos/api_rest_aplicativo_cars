package com.viniciussantos.SpringRestAPI.domain

import com.viniciussantos.SpringRestAPI.domain.TravelRequest
import com.viniciussantos.SpringRestAPI.domain.TravelRequestRepository
import org.springframework.stereotype.Component


@Component
class TravelService(val travelRequestRepository: TravelRequestRepository) {
    fun saveTravelRequest(travelRequest: TravelRequest) = travelRequestRepository.save(travelRequest)

}