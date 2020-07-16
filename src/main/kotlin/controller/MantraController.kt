package controller

import fanta.model.MatchStats
import fanta.model.PlayerStat
import fanta.service.FantaMatchCenterParser
import fanta.service.MantraFutMobService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MantraController {

    val fantaScrapper: FantaMatchCenterParser = FantaMatchCenterParser()
    val mantraFutMob = MantraFutMobService()

    @GetMapping("/scrapper/mantra/matchplayer")
    fun getMatchPlayersInfo(): List<PlayerStat> =
            fantaScrapper.parseMatchByURL("www.fotmob.com/livescores/3056054/lineup")

    @GetMapping("/scrapper/mantra/match")
    fun getMatchInfo(): MatchStats =
            fantaScrapper.parseMatchFactsByURL("www.fotmob.com/livescores/3056079/matchfacts")

    @GetMapping("/scrapper/mantra/rounds")
    fun getPLRounds(@RequestParam(value = "round") round: Int): String =
            mantraFutMob.getRankingForRound(round)

    @GetMapping("/scrapper/mantra/rounds/facts")
    fun getMatchFactsRounds(@RequestParam(value = "round") round: Int): String =
            mantraFutMob.getMatchFactsForRound(round)
}