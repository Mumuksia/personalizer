package rozha.service

import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Parser
import org.springframework.stereotype.Component
import rozha.model.ClanInfo
import rozha.model.ClanMember
import rozha.model.ClanMemberOverview
import rozha.model.clanwar.Clan
import rozha.model.clanwar.ClanWar
import rozha.model.clanwar.ClanWarMember
import rozha.model.clanwar.War
import rozha.model.league.LeagueGroup
import java.io.StringReader

@Component
class ClashAPIConverter {

    fun convertClanInfo(clanInfo: String): String {
        val klaxon = Klaxon()

        val result = klaxon.parse<ClanInfo>(clanInfo)?.let { cm -> klaxon.toJsonString(cm) }

        return klaxon.toJsonString(result.orEmpty())
    }

    fun convertClanMembers(clanInfo: String): String {
        val result = arrayListOf<ClanMemberOverview>()
        val klaxon = Klaxon()

        val parser: Parser = Parser.default()
        val json: JsonObject = parser.parse(StringBuilder(clanInfo)) as JsonObject
        json.array<String>("items")?.let {
            JsonReader(StringReader(it.toJsonString())).use { reader ->
                reader.beginArray {
                    while (reader.hasNext()) {
                        klaxon.parse<ClanMemberOverview>(reader)?.let { person ->
                            //val icon = (parser.parse(reader) as JsonObject).string("league.iconUrls.small")
                            result.add(person)
                        }
                    }
                }
            }
        }

        return klaxon.toJsonString(result)
    }

    fun convertMember(clanInfo: String): String {
        val klaxon = Klaxon()

        val result = klaxon.parse<ClanMember>(clanInfo)?.let { cm -> klaxon.toJsonString(cm) }

        return klaxon.toJsonString(result.orEmpty())
    }

    fun convertClanWar(clanInfo: String): String {
        val klaxon = Klaxon()

        val clan = getClanForClanWar(clanInfo, "clan")
        val opponent = getClanForClanWar(clanInfo, "opponent")
        val war = klaxon.parse<War>(clanInfo)
        val clanWarMembers = getClanWarMembers(clanInfo, "$.clan.members")
        val opponentClanWarMembers = getClanWarMembers(clanInfo, "$.opponent.members")

        if (clan != null && war != null && opponent != null) {
            return klaxon.toJsonString(ClanWar(clan, opponent, war, clanWarMembers, opponentClanWarMembers))
        }

        return ""
    }

    fun getClanForClanWar(info: String, clan: String): Clan? {
        val klaxon = Klaxon()
        val parser: Parser = Parser.default()
        val json: JsonObject = parser.parse(StringBuilder(info)) as JsonObject

        val clanInfo = json.obj(clan)
        if (clanInfo != null)
            return klaxon.parse<Clan>(clanInfo.toJsonString())
        return null
    }

    fun getClanWarMembers(info: String, clan: String): List<ClanWarMember> {
        val result = arrayListOf<ClanWarMember>()

        val klaxon = Klaxon()
        val parser: Parser = Parser.default()
        val json: JsonObject = parser.parse(StringBuilder(info)) as JsonObject

        val clanInfo = json.array<String>(clan)

        clanInfo?.let {JsonReader(StringReader(it.toJsonString())).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    klaxon.parse<ClanWarMember>(reader)?.let { person ->
                        result.add(person)
                    }
                }
            }
        } }
        return result
    }

    fun convertLeagueGroup(clanInfo: String): String {
        val klaxon = Klaxon()

        val result = klaxon.parse<LeagueGroup>(clanInfo)?.let { cm -> klaxon.toJsonString(cm) }

        return klaxon.toJsonString(result.orEmpty())
    }

    fun convertLeagueWar(clanInfo: String): String {
        return convertClanWar(clanInfo)
    }

}