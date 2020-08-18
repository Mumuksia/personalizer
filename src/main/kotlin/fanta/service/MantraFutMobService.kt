package fanta.service

import fanta.model.MatchFact
import fanta.model.PlayerStat
import fanta.ugly.Matches
import klaxon.KlaxonService
import org.springframework.stereotype.Component
import java.io.File

@Component
class MantraFutMobService {

    val mantraParser = MantraFutMobParser()
    val matchCenterParser = FantaMatchCenterParser()
    val klaxonService = KlaxonService()

    fun getRankingForRound(round: Int): String {
        val result = arrayListOf<PlayerStat>()

        val roundContent = getJsonStringForRound(round)
        val matches = mantraParser.convertMatchLinksForRound(roundContent)

        matches.forEach { result.addAll(matchCenterParser.parseMatchByURL("www.fotmob.com" + it.pageUrl + "/lineup", it.home, it.away)) }

        return klaxonService.toJsonString(result)
    }


    fun getMatchFactsForRound(round: Int): String {
        val result = arrayListOf<MatchFact>()

        val roundContent = getJsonStringForRound(round)
        val matches = mantraParser.convertMatchLinksForRound(roundContent)

        matches.forEach {
            result.add(MatchFact(it.home, it.away,
                    matchCenterParser.parseMatchFactsByURL("www.fotmob.com" + it.pageUrl + "/matchfacts", it.home, it.away)))
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
            1 -> return Matches.pl1
            2 -> return Matches.pl2
            3 -> return Matches.pl3
            4 -> return Matches.pl4
            5 -> return Matches.pl5
            6 -> return Matches.pl6
            7 -> return Matches.pl7
            8 -> return Matches.pl8
            9 -> return Matches.pl9
            10 -> return Matches.pl10
            11 -> return Matches.pl11
            12 -> return Matches.pl12
            13 -> return Matches.pl13
            14 -> return Matches.pl14
            15 -> return Matches.pl15
            16 -> return Matches.pl16
            17 -> return Matches.pl17
            18 -> return Matches.pl18
            19 -> return Matches.pl19
            20 -> return Matches.pl20
            21 -> return Matches.pl21
            22 -> return Matches.pl22
            23 -> return Matches.pl23
            24 -> return Matches.pl24
            25 -> return Matches.pl25
            26 -> return Matches.pl26
            27 -> return Matches.pl27
            28 -> return Matches.pl28
            29 -> return Matches.pl29
            30 -> return Matches.pl30
            31 -> return Matches.pl31
            32 -> return Matches.pl32
            33 -> return Matches.pl33
            34 -> return Matches.pl34
            35 -> return Matches.pl35
            36 -> return Matches.pl36
            37 -> return Matches.pl37
            38 -> return Matches.pl38
            else -> {
                return ""
            }
        }
    }
}