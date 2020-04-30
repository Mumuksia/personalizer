package rozha.service

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


@Component
class ClashAPIService {

    val converter = ClashAPIConverter()
    val clanUrl = "https://api.clashofclans.com/v1/clans/"
    val playerUrl = "https://api.clashofclans.com/v1/players/"

    val accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjU4Y2Y3NDJlLWExZGUtNGNlYS1iOGNkLTM2MjdmMDM4M2FlZSIsImlhdCI6MTU4NDY1NjE4OSwic3ViIjoiZGV2ZWxvcGVyLzVkODk3MmYwLTMxODItNjA2Mi03NmEyLWUwMmU1NTVhMWE3NCIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjEzMi4xNDUuMjU0LjExNSIsIjgzLjI0OC4xNjYuOTEiXSwidHlwZSI6ImNsaWVudCJ9XX0.GZl_e4mxhaHkG0SDS_i3QMa37UG7-QwZD1t-yzoewiRPzlIVaNuj_eXQJwWnDtGbDLbHR4C5UCKQak_mxT6LFw"

    val cloudToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjA2MWViNTJkLTBhNmEtNDZmMS04MmFjLWEyMTFkM2EzZGE3ZCIsImlhdCI6MTU4NDE5MDM2NCwic3ViIjoiZGV2ZWxvcGVyLzVkODk3MmYwLTMxODItNjA2Mi03NmEyLWUwMmU1NTVhMWE3NCIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjgzLjI0OC4xNjYuOTEiXSwidHlwZSI6ImNsaWVudCJ9XX0.wlUqOjWba57J1TXBmM2BG5178nBPpQ7Jj59DuHOud6ITxQW_5EqwihXwAC9_F2q71OJzabtxMtwirt9Zdkaasw"

    fun getClanMembers(clanId: String) : String {

        val headers = HttpHeaders()
        headers["Accept"] = "application/json"
        headers["authorization"] = "Bearer $accessToken"

        val httpEntity: HttpEntity<*> = HttpEntity<Any>(headers)
        println("$clanUrl$clanId/members")

        val uriComponents = UriComponentsBuilder.fromUriString("$clanUrl{clanId}/members").build()
                .expand(clanId)
                .encode()

       val resp = RestTemplate().exchange(uriComponents.toUri(), HttpMethod.GET, httpEntity, String::class.java)

        if (resp.statusCode == HttpStatus.OK){
            return converter.convertClanMembers(resp.body)
        }
        return ""
    }

    fun getClanInfo(clanId: String) : String {

        val headers = HttpHeaders()
        headers["Accept"] = "application/json"
        headers["authorization"] = "Bearer $accessToken"

        val httpEntity: HttpEntity<*> = HttpEntity<Any>(headers)
        println("$clanUrl$clanId")

        val uriComponents = UriComponentsBuilder.fromUriString("$clanUrl{clanId}").build()
                .expand(clanId)
                .encode()

        val resp = RestTemplate().exchange(uriComponents.toUri(), HttpMethod.GET, httpEntity, String::class.java)

        if (resp.statusCode == HttpStatus.OK){
            return converter.convertClanInfo(resp.body)
        }
        return ""
    }

    fun getMember(playerId: String) : String {

        val headers = HttpHeaders()
        headers["Accept"] = "application/json"
        headers["authorization"] = "Bearer $accessToken"

        val httpEntity: HttpEntity<*> = HttpEntity<Any>(headers)
        println("$playerUrl$playerId")

        val uriComponents = UriComponentsBuilder.fromUriString("$playerUrl{player}").build()
                .expand(playerId)
                .encode()

        val resp = RestTemplate().exchange(uriComponents.toUri(), HttpMethod.GET, httpEntity, String::class.java)

        if (resp.statusCode == HttpStatus.OK){
            println(resp.body)
            return converter.convertMember(resp.body)
        }
        return ""
    }

    fun getClanWar(clanId: String) : String {

        val headers = HttpHeaders()
        headers["Accept"] = "application/json"
        headers["authorization"] = "Bearer $accessToken"

        val httpEntity: HttpEntity<*> = HttpEntity<Any>(headers)
        println("$clanUrl$clanId/currentwar")

        val uriComponents = UriComponentsBuilder.fromUriString("$clanUrl{clanId}/currentwar").build()
                .expand(clanId)
                .encode()

        val resp = RestTemplate().exchange(uriComponents.toUri(), HttpMethod.GET, httpEntity, String::class.java)

        if (resp.statusCode == HttpStatus.OK){
            return converter.convertClanWar(resp.body)
        }
        return ""
    }

    fun getClanLeagueTemp(clanId: String) : String {

        val headers = HttpHeaders()
        headers["Accept"] = "application/json"
        headers["authorization"] = "Bearer $accessToken"

        val httpEntity: HttpEntity<*> = HttpEntity<Any>(headers)
        println("$clanUrl$clanId")

        val uriComponents = UriComponentsBuilder.fromUriString("$clanUrl{clanId}/currentwar/leaguegroup").build()
                .expand(clanId)
                .encode()

        //TODO handle not found here
        val resp = RestTemplate().exchange(uriComponents.toUri(), HttpMethod.GET, httpEntity, String::class.java)

        if (resp.statusCode == HttpStatus.OK){
            return resp.body
        }
        return ""
    }
}