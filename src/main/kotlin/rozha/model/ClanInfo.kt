package rozha.model

import com.beust.klaxon.Json

data class ClanInfo(val tag: String, val name: String, val clanLevel: Int,
                    val clanPoints: Int, val warWins: Int, val warLosses: Int,
                    val members: Int, val description: String, @Json(path = "$.location.name") val location: String)