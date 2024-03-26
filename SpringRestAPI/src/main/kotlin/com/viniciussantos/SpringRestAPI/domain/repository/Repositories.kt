package com.viniciussantos.SpringRestAPI.domain.repository

import com.viniciussantos.SpringRestAPI.domain.entity.Driver
import com.viniciussantos.SpringRestAPI.domain.entity.Passenger
import com.viniciussantos.SpringRestAPI.domain.entity.TravelRequest
import org.springframework.data.jpa.repository.JpaRepository

interface DriverRepository : JpaRepository<Driver, Long>
interface PassengerRepository: JpaRepository<Passenger, Long>
interface TravelRequestRepository : JpaRepository<TravelRequest, Long>