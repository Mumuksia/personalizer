package rozha.model.league

import com.beust.klaxon.Json

data class LeagueClan(val tag: String, val name: String, val clanLevel: Int,
                      /*@Json(path = "$.badgeUrls.small") val icon: String,*/ val members: List<LeagueMember>)