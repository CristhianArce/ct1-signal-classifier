package com.ccs.ct1signalclassifier.dto

import java.time.Instant

/**
 * SignalType, could be one out of three types.
 */
enum class SignalType {
    EMERGENCY, INFO, ANOMALOUS
}

/**
 * Helps to specify speed measure.
 */
enum class SpeedType {
    KMH, MPH
}

/**
 * Object that is received by the REST api.
 */
data class Signal(
        val type: SignalType,
        val plate: String,
        val timestamp: Instant,
        val location: Location?,
        val extras: Extras?
)

data class Location(
        val latitude: Double,
        val longitude: Double,
        val altitude: Double,
        val speed: Float,
        val speedType: SpeedType
)

data class Extras(
        val temperature: Float,
        val humidity: Float
)