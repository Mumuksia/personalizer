package fanta.service

import com.beust.klaxon.Klaxon
import fanta.model.MatchEvent
import fanta.model.MatchLink
import fanta.model.MatchStats
import fanta.model.PlayerStat
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class FantaMatchCenterParser {

    fun parseMatchByURL(url: String): List<PlayerStat> {
        var site = url
        if (!url.startsWith("https")) //add https
            site = "https://$url"

        Jsoup.connect(site).get().run {
            return parseMatchElements(select("button." + "css-lw53vx-PlayerContainer-applyHover-layout")
            )
        }
    }

    fun parseMatchFactsByURL(url: String): MatchStats {
        var site = url
        if (!url.startsWith("https")) //add https
            site = "https://$url"

        Jsoup.connect(site).get().run {
            //div id match-centre -> div class player
            return parseMatchFacts(select("div." + "css-4iek54-itemView-itemLayout-eventItemWrapper"))
        }
    }

    fun parsePLRounds(): String {
        val klaxon = Klaxon()

        return klaxon.toJsonString(parseRounds())
    }

    fun parseRounds(): List<MatchLink> {
        val roundsURL = "https://www.fotmob.com/leagues/47/matches"

        Jsoup.connect(roundsURL).get().run {
            return select("div." + "css-1rxdjrc-TLMatchCSS-applyHover").map { el -> MatchLink("", "", el.select("a[class~=MatchCSS]").attr("href")) }

        }
    }

    fun parseRounds(matches: List<Pair<String, String>>): List<MatchLink> {
        val roundsURL = "https://www.fotmob.com/leagues/47/matches"

        Jsoup.connect(roundsURL).get().run {
            //div id match-centre -> div class player
            val allMatches = select("div." + "css-1rxdjrc-TLMatchCSS-applyHover").map { el -> MatchLink("", "", el.select("a[class~=MatchCSS]").attr("href")) }
            return matches.map { m -> getMatchLink(m, allMatches) }
        }
    }

    fun parseMatchElements(els: Elements): List<PlayerStat> {
        return els.stream().map { e ->
            PlayerStat(e.select("span[class~=PlayerRatingStyled]").text(),
                    e.select("span[class~=playerName]").textNodes().get(0).text())
        }.collect(Collectors.toList())
    }

    fun parseMatchFacts(els: Elements): MatchStats {
        val events = arrayListOf<MatchEvent>()
        els.stream().forEach() { e ->
            events.add(parseMatchEvent(e))
        }
        return MatchStats(events)
    }

    fun parseMatchEvent(e: Element): MatchEvent {
        val players = e.select("div[class~=playersLayoutWrapper]").select("a[class~=eventPlayerName]")
        val time = e.select("span[class~=timeLayout]").select("span[class~=eventTime]").text()


        if (players.size == 1){
            if (players[0].text().contains("Own Goal")){
                return MatchEvent("Own Goal", time, players.text().split(",")[0], "")
            }

            if (e.select("div[class~=playersLayoutWrapper]").select("a[class~=assist]").size == 1)
                return MatchEvent("goal", time, players.text(),
                        e.select("div[class~=playersLayoutWrapper]").select("a[class~=assist]").text().removePrefix("assist by"))
            return MatchEvent("goal", time, players.text(), "")
        }


        if (players.size == 2)
            return MatchEvent("substitution", time, players[0].text(), players[1].text())

        if (e.select("i[class~=redCardIcon]").size == 1)
            return MatchEvent("redCard", time, e.select("a[class~=eventPlayerName]").text(), "")
        if (e.select("i[class~=yellowCardIcon]").size == 1)
            return MatchEvent("yellowCard", time, e.select("a[class~=eventPlayerName]").text(), "")
        return MatchEvent("", "", "", "")
    }

    fun getMatchLink(match: Pair<String, String>, storage: List<MatchLink>): MatchLink {
        return storage.first { m -> (m.home == match.first && m.away == match.second) }
    }
}