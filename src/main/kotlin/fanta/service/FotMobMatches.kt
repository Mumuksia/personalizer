package fanta.service

import fanta.model.MatchLink
import fanta.model.Round
import klaxon.KlaxonService
import org.jsoup.Jsoup
import org.springframework.stereotype.Component
import java.io.File

@Component
class FotMobMatches {

    val klaxonService = KlaxonService()

    fun parsePLRounds(): String {
        val matchLinks = addTeams(parseRounds())
        createRounds(matchLinks)
        return "success"
    }

    fun parseRounds(): List<MatchLink> {
        val roundsURL = "https://www.fotmob.com/leagues/47/matches/premier-league"

        Jsoup.connect(roundsURL).get().run {
            return select("div." + "css-1rxdjrc-TLMatchCSS-applyHover").map { el -> MatchLink("", "", el.select("a[class~=MatchCSS]").attr("href")) }

        }
    }

    fun addTeams(ml: List<MatchLink>): List<MatchLink> {
        return ml.map { m -> MatchLink(getHomeAwayTeam(m.pageUrl)[0], getHomeAwayTeam(m.pageUrl)[1], m.pageUrl) }.toList()
    }

    fun getHomeAwayTeam(matchUrl: String): List<String> {
        return matchUrl.replaceBeforeLast("/", "").replace("-", " ").replace("/", "").split(" vs ")
    }

    fun createRounds(ml: List<MatchLink>) {
        for (i in 1..38) {
            val round = Round(ml.subList((i - 1) * 10, i * 10))
            File("plRound$i.json").writeText(klaxonService.toJsonString(round))
        }
    }

}