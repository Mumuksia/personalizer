package klaxon

import com.beust.klaxon.Klaxon
import org.springframework.stereotype.Service

@Service
class KlaxonService {

    val klaxon = Klaxon()

    fun toJsonString(value: Any?): String {
        return klaxon.toJsonString(value)
    }
}