package rozha.model.clanwar

data class Clan(val clanLevel: Int, val attacks: Int,
                val stars: Int, val destructionPercentage: Double,
                val tag: String, val members: List<ClanWarMember> = arrayListOf(), val name: String = "test")