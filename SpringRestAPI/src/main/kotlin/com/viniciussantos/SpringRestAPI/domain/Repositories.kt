package com.viniciussantos.SpringRestAPI.domain

import com.viniciussantos.SpringRestAPI.domain.Driver
import com.viniciussantos.SpringRestAPI.domain.Passenger
import com.viniciussantos.SpringRestAPI.domain.TravelRequest
import org.springframework.data.jpa.repository.JpaRepository

interface DriverRepository : JpaRepository<Driver, Long>
interface PassengerRepository: JpaRepository<Passenger, Long>
interface TravelRequestRepository : JpaRepository<TravelRequest, Long> {
    fun findByStatus(status: TravelRequestStatus): List<TravelRequest>
}