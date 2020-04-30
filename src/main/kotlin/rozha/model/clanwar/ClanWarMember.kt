package rozha.model.clanwar

data class ClanWarMember(val tag: String, val name: String, val townhallLevel: Int, val mapPosition: Int,
                         val attacks: List<WarAttack> = arrayListOf())