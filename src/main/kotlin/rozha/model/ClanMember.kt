package rozha.model

data class ClanMember(val tag: String, val name: String, val bestTrophies: Int,
                              val donationsReceived: Int, val donations: Int,
                              val role: String, val trophies: Int, val warStars: Int)