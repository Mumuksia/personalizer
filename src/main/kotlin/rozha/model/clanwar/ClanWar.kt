package rozha.model.clanwar

data class ClanWar(val clan: Clan, val opponent: Clan, val war: War,
                   val clanWarAttacks: List<ClanWarMember>, val opponentClanWarAttacks: List<ClanWarMember>)