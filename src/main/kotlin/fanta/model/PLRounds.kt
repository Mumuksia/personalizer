package fanta.model

import com.beust.klaxon.Json

data class PLRounds(@Json(path = "$.team1.name") val home: String, @Json(path = "$.team2.name")  val away: String)