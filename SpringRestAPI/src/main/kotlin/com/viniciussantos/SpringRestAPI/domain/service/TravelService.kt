package com.viniciussantos.SpringRestAPI.domain.service

import com.viniciussantos.SpringRestAPI.domain.entity.TravelRequest
import com.viniciussantos.SpringRestAPI.domain.repository.TravelRequestRepository
import org.springframework.stereotype.Component


@Component
class TravelService(val travelRequestRepository: TravelRequestRepository) {
    fun saveTravelRequest(travelRequest: TravelRequest) = travelRequestRepository.save(travelRequest)

}