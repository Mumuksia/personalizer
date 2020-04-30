package rozha.model

import com.beust.klaxon.Json

data class ClanMemberOverview(val tag: String, val name: String, val clanRank: Int,
                              val donationsReceived: Int, val donations: Int,
                              val role: String, val trophies: Int, @Json(path = "$.league.iconUrls.small") val icon: String)