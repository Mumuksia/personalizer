package fanta.service

import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import fanta.model.MatchFact
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
        File("avgRanking.json").writeText(klaxon.toJsonString(convertTotalRankingToAvg(dataMap)))
        return "success"
    }

    fun getTotalFacts(): HashMap<String, Double> {
        val klaxon = Klaxon()
        val dataMap = HashMap<String, Double>()

        for (i in 1..37) {
            val data = File(Paths.get("").toAbsolutePath().toString() + "/src/main/resources/mantra/fact$i.json").readText()

            JsonReader(StringReader(data)).use { reader ->
                reader.beginArray {
                    while (reader.hasNext()) {
                        val matchFact = klaxon.parse<MatchFact>(reader)
                        matchFact?.facts?.events?.forEach { me ->
                            val playerName = me.playerName.replace(", Penalty", "")
                            dataMap.putIfAbsent(playerName, 0.0)
                            if (me.type.contains("goal")) {
                                if (me.secondPlayerName != "") {
                                    dataMap.putIfAbsent(me.secondPlayerName, 0.0)
                                    val assist = dataMap[me.secondPlayerName]
                                    if (assist != null) {
                                        dataMap[me.secondPlayerName] = assist + 1
                                    }

                                }
                                val goals = dataMap.get(playerName)
                                if (goals != null) {
                                    dataMap[playerName] = goals + 3
                                }
                            }
                        }
                    }
                }
            }
        }
        File("totalFacts.json").writeText(klaxon.toJsonString(dataMap.toList().sortedBy { (_, value) -> value }.toMap()))
        return dataMap
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



        File("totalRankingCombined.json").writeText(klaxon.toJsonString((convertTotalRankingToTotalMap(dataMap).asSequence() + getTotalFacts().asSequence()).distinct()
                .groupBy({ it.key }, { it.value }).mapValues { (_, values) -> values.sumByDouble { it } }.toList().sortedBy { (_, value) -> value }.toMap()))


        //fold(0.0){accumulator, element -> accumulator + element.value }))
        return "success"
    }

    fun convertTotalRankingToTotal(data: HashMap<String, MutableList<String>>): String {
        val klaxon = Klaxon()
        val result = HashMap<String, Double>()
        data.keys.forEach { result[it] = getTotal(data.getValue(it)) }
        val sorted = result.toList().sortedBy { (_, value) -> value }.toMap()
        return klaxon.toJsonString(sorted)
    }

    fun convertTotalRankingToTotalMap(data: HashMap<String, MutableList<String>>): Map<String, Double> {
        val result = HashMap<String, Double>()
        data.keys.forEach { result[it] = getTotal(data.getValue(it)) }
        val sorted = result.toList().sortedBy { (_, value) -> value }.toMap()
        return sorted
    }

    fun getTotal(vals: List<String>): Double {
        var sum = 0.0
        vals.forEach { sum += it.toFloat() }
        return sum
    }
}