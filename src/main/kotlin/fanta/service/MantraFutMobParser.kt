package fanta.service

import com.beust.klaxon.*
import fanta.model.MatchLink
import fanta.model.PLRounds
import fanta.model.Round
import org.springframework.stereotype.Component
import java.io.StringReader

@Component
class MantraFutMobParser {

    fun convertMatchLinks(futMob: String): List<MatchLink> {
        val result = arrayListOf<MatchLink>()
        val klaxon = Klaxon()

        val parser: Parser = Parser.default()
        val json: JsonObject = parser.parse(StringBuilder(futMob)) as JsonObject
        (json.get("data") as JsonObject).array<String>("fixtures")?.let {
            JsonReader(StringReader(it.toJsonString())).use { reader ->
                reader.beginArray {
                    while (reader.hasNext()) {
                        klaxon.parse<MatchLink>(reader)?.let { match ->
                            result.add(match)
                        }
                    }
                }
            }
        }

        return result
    }

    fun convertMatchPLRounds(plRounds: String): List<PLRounds> {
        var result = arrayListOf<PLRounds>()
        val klaxon = Klaxon()

        val parser: Parser = Parser.default()
        val json: JsonObject = parser.parse(StringBuilder(plRounds)) as JsonObject
        json.array<String>("matches")?.let {
            JsonReader(StringReader(it.toJsonString())).use { reader ->
                reader.beginArray {
                    while (reader.hasNext()) {
                        klaxon.parse<PLRounds>(reader)?.let { matchDay ->
                            result.add(matchDay)
                        }
                    }
                }
            }
        }

        return result
    }

    fun convertMatchLinksForRound(plRounds: String): List<MatchLink> {
        var result = arrayListOf<MatchLink>()
        val klaxon = Klaxon()

        val parser: Parser = Parser.default()
        val json: JsonObject = parser.parse(StringBuilder(plRounds)) as JsonObject
        json.array<String>("matches")?.let {
            JsonReader(StringReader(it.toJsonString())).use { reader ->
                reader.beginArray {
                    while (reader.hasNext()) {
                        klaxon.parse<MatchLink>(reader)?.let { matchDay ->
                            result.add(matchDay)
                        }
                    }
                }
            }
        }

        return result
    }
}