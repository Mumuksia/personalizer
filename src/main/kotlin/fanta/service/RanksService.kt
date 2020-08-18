package fanta.service

import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import fanta.model.PlayerStat
import org.springframework.stereotype.Component
import java.io.File
import java.io.StringReader
import java.nio.file.Paths

@Component
class RanksService {

    fun getAvgRanking(): String {
        val klaxon = Klaxon()
        val dataMap = HashMap<String, MutableList<String>>()

        for (i in 1..37) {
            val data = File(Paths.get("").toAbsolutePath().toString() + "/src/main/resources/mantra/rank$i.json").readText()

            JsonReader(StringReader(data)).use { reader ->
                reader.beginArray {
                    while (reader.hasNext()) {
                        val playerStat = klaxon.parse<PlayerStat>(reader)
                        if (playerStat != null) {
                            if (playerStat.stat != "")
                                if (dataMap.containsKey(playerStat.name)) {
                                    dataMap.getValue(playerStat.name).add(playerStat.stat)
                                } else {
                                    dataMap.putIfAbsent(playerStat.name, arrayListOf(playerStat.stat))
                                }
                        }
                    }
                }
            }
        }
        return convertTotalRankingToAvg(dataMap)
    }

    fun convertTotalRankingToAvg(data: HashMap<String, MutableList<String>>): String {
        val klaxon = Klaxon()
        val result = HashMap<String, Double>()
        data.keys.forEach { result[it] = getAvg(data.getValue(it)) }
        val sorted = result.toList().sortedBy { (_, value) -> value }.toMap()
        return klaxon.toJsonString(sorted)
    }

    fun getAvg(vals: List<String>): Double {
        var sum = 0.0
        vals.forEach { sum += it.toFloat() }
        return sum / vals.size
    }

    fun getTotalRanking(): String {
        val klaxon = Klaxon()
        val dataMap = HashMap<String, MutableList<String>>()

        for (i in 1..37) {
            val data = File(Paths.get("").toAbsolutePath().toString() + "/src/main/resources/mantra/rank$i.json").readText()

            JsonReader(StringReader(data)).use { reader ->
                reader.beginArray {
                    while (reader.hasNext()) {
                        val playerStat = klaxon.parse<PlayerStat>(reader)
                        if (playerStat != null) {
                            if (playerStat.stat != "")
                                if (dataMap.containsKey(playerStat.name)) {
                                    dataMap.getValue(playerStat.name).add(playerStat.stat)
                                } else {
                                    dataMap.putIfAbsent(playerStat.name, arrayListOf(playerStat.stat))
                                }
                        }
                    }
                }
            }
        }
        return convertTotalRankingToTotal(dataMap)
    }

    fun convertTotalRankingToTotal(data: HashMap<String, MutableList<String>>): String {
        val klaxon = Klaxon()
        val result = HashMap<String, Double>()
        data.keys.forEach { result[it] = getTotal(data.getValue(it)) }
        val sorted = result.toList().sortedBy { (_, value) -> value }.toMap()
        return klaxon.toJsonString(sorted)
    }

    fun getTotal(vals: List<String>): Double {
        var sum = 0.0
        vals.forEach { sum += it.toFloat() }
        return sum
    }
}