package com.example.weather.internal

class BearingToDirection(val windBearing: Int) {

    fun Direction(): String{

        when(windBearing) {
            0 -> return "Calm"
            in 1..11 -> return "N"
            in 12..34 -> return "NNE"
            in 35..56 -> return "NE"
            in 37..79 -> return "ENE"
            in 80..101 -> return "E"
            in 102..124 -> return "ESE"
            in 125..146 -> return "SE"
            in 147..169 -> return "SSE"
            in 170..191 -> return "S"
            in 192..214 -> return "SSW"
            in 215..236 -> return "SW"
            in 237..259 -> return "WSW"
            in 260..281 -> return "W"
            in 282..304 -> return "WNW"
            in 305..326 -> return "NW"
            in 327..349 -> return "NNW"
            in 350..360 -> return "N"
            else -> return "Unknown"
        }

    }
}