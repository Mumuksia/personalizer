package rozha.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import rozha.service.ClashAPIService

@RestController
class ClashAPIController {

    val clashApiService = ClashAPIService()

    @CrossOrigin()
    @GetMapping("/clan")
    fun getClanInformation(@RequestParam(value = "clanId") clanId: String) : String =
            clashApiService.getClanInfo(clanId)

    @CrossOrigin()
    @GetMapping("/clan/members")
    fun getClanMembers(@RequestParam(value = "clanId") clanId: String) : String =
            clashApiService.getClanMembers(clanId)


    @CrossOrigin()
    @GetMapping("/clan/war")
    fun getClanCurrentWar(@RequestParam(value = "clanId") clanId: String) : String =
            clashApiService.getClanWar(clanId)


    @CrossOrigin()
    @GetMapping("/clan/user")
    fun getClanPlayerInfo(@RequestParam(value = "userId") userId: String) : String =
            clashApiService.getMember(userId)

    @CrossOrigin()
    @GetMapping("/clan/league")
    fun getClanLeagueGroup(@RequestParam(value = "clanId") clanId: String) : String =
            clashApiService.getClanLeagueTemp(clanId)

    @CrossOrigin()
    @GetMapping("/clan/league/war")
    fun getClanLeagueWar(@RequestParam(value = "warTag") warTag: String) : String =
            clashApiService.getClanLeagueWar(warTag)


}