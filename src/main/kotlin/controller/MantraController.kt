package controller

import fanta.model.MatchStats
import fanta.model.PlayerStat
import fanta.service.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MantraController {

    val fantaScrapper: FantaMatchCenterParser = FantaMatchCenterParser()
    val mantraFutMob = MantraFutMobService()
    val ranksService = RanksService()
    val fotMobMatches = FotMobMatches()
    val mantraFutMobServiceCurrent = MantraFutMobServiceCurrent()

    @GetMapping("/scrapper/mantra/matchplayer")
    fun getMatchPlayersInfo(): List<PlayerStat> =
            fantaScrapper.parseMatchByURL("www.fotmob.com/livescores/3056054/lineup")

    @GetMapping("/scrapper/mantra/match")
    fun getMatchInfo(): MatchStats =
            fantaScrapper.parseMatchFactsByURL("www.fotmob.com/livescores/3056079/matchfacts")

    @GetMapping("/scrapper/mantra/2019/rounds")
    fun getPLRounds(@RequestParam(value = "round") round: Int): String =
            mantraFutMob.getRankingForRound(round)

    @GetMapping("/scrapper/mantra/rounds/all")
    fun getAllRankings(): String =
            mantraFutMob.createAllRankings()

    @GetMapping("/scrapper/mantra/rounds/all/avg")
    fun getAvgRanking(): String =
            ranksService.getAvgRanking()

    @GetMapping("/scrapper/mantra/rounds/all/total")
    fun getTotalRanking(): String =
            ranksService.getTotalRanking()

    @GetMapping("/scrapper/mantra/2019/rounds/facts")
    fun getMatchFactsRounds2019(@RequestParam(value = "round") round: Int): String =
            mantraFutMob.getMatchFactsForRound(round)

    @GetMapping("/scrapper/mantra/rounds/facts")
    fun getMatchFactsRounds(@RequestParam(value = "round") round: Int): String =
            mantraFutMobServiceCurrent.getMatchFactsForRound(round)

    @GetMapping("/scrapper/mantra/rounds")
    fun getRanksForRounds(@RequestParam(value = "round") round: Int): String =
            mantraFutMobServiceCurrent.getRankingForRound(round)

    @GetMapping("/scrapper/mantra/pl2021/links")
    fun getMatchLinks(): String =
            fotMobMatches.parsePLRounds()
}