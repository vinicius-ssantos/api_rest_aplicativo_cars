package com.viniciussantos.SpringRestAPI.interfaces.outcoming

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class GMapsService(@Value("\${app.car.domain.googlemaps.apikey}") val appKey: String) {
    val GMAPS_TEMPLATE: String = "https://maps.googleapis.com/maps/api/directions/json?origin={origin}&destination={destination}&key={key}"
    fun getDistanceBetweenAddresses(addressOne: String, addressTwo: String): Int {
        return 0
    }
}