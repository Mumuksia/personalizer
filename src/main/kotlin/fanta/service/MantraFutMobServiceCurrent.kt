package fanta.service

import fanta.model.MatchFact
import fanta.model.PlayerStat
import fanta.ugly.Matches2021
import klaxon.KlaxonService
import org.springframework.stereotype.Component
import java.io.File

@Component
class MantraFutMobServiceCurrent {

    val mantraParser = MantraFutMobParser()
    val matchCenterParser = FantaMatchCenterParser()
    val klaxonService = KlaxonService()

    fun getRankingForRound(round: Int): String {
        val result = arrayListOf<PlayerStat>()

        val roundContent = getJsonStringForRound(round)
        val matches = mantraParser.convertMatchLinksForRound(roundContent)

        matches.forEach {
            result.addAll(matchCenterParser.parseMatchByURL("www.fotmob.com" + it.pageUrl.replace("matchfacts", "lineup"), it.home, it.away))
        }

        return klaxonService.toJsonString(result)
    }


    fun getMatchFactsForRound(round: Int): String {
        val result = arrayListOf<MatchFact>()

        val roundContent = getJsonStringForRound(round)
        val matches = mantraParser.convertMatchLinksForRound(roundContent)

        matches.forEach {
            result.add(MatchFact(it.home, it.away,
                    matchCenterParser.parseMatchFactsByURL("www.fotmob.com" + it.pageUrl, it.home, it.away)))
        }


        return klaxonService.toJsonString(result)
    }

    fun createAllRankings(): String {
        for (i in 1..38) {
            println("getting data for round $i")
            File("rank$i.json").writeText(getRankingForRound(i))
            Thread.sleep(5000)
        }

        return ""
    }

    fun getJsonStringForRound(round: Int): String {
        when (round) {
            1 -> return Matches2021.pl1
            2 -> return Matches2021.pl2
            3 -> return Matches2021.pl3
            4 -> return Matches2021.pl4
            5 -> return Matches2021.pl5
            6 -> return Matches2021.pl6
            7 -> return Matches2021.pl7
            8 -> return Matches2021.pl8
            9 -> return Matches2021.pl9
            10 -> return Matches2021.pl10
            11 -> return Matches2021.pl11
            12 -> return Matches2021.pl12
            13 -> return Matches2021.pl13
            14 -> return Matches2021.pl14
            15 -> return Matches2021.pl15
            16 -> return Matches2021.pl16
            17 -> return Matches2021.pl17
            18 -> return Matches2021.pl18
            19 -> return Matches2021.pl19
            20 -> return Matches2021.pl20
            21 -> return Matches2021.pl21
            22 -> return Matches2021.pl22
            23 -> return Matches2021.pl23
            24 -> return Matches2021.pl24
            25 -> return Matches2021.pl25
            26 -> return Matches2021.pl26
            27 -> return Matches2021.pl27
            28 -> return Matches2021.pl28
            29 -> return Matches2021.pl29
            30 -> return Matches2021.pl30
            31 -> return Matches2021.pl31
            32 -> return Matches2021.pl32
            33 -> return Matches2021.pl33
            34 -> return Matches2021.pl34
            35 -> return Matches2021.pl35
            36 -> return Matches2021.pl36
            37 -> return Matches2021.pl37
            38 -> return Matches2021.pl38
            else -> {
                return ""
            }
        }
    }
}