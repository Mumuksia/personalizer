package rozha.service

import com.beust.klaxon.JsonArray
import com.beust.klaxon.Parser
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.io.File
import java.nio.file.Paths

@RunWith(JUnitPlatform::class)
class ClashAPIConverterMembersTest  : Spek({
    describe("convert clash members responses") {
        val clashAPIConverter = ClashAPIConverter()

        println(Paths.get("").toAbsolutePath().toString())
        it("response should have 43 members") {
            val testContent = File(Paths.get("").toAbsolutePath().toString() +
                    "/src/test/resources/rozha/clanMembers.json").readText()
            val testee = Parser.default().parse(StringBuilder(clashAPIConverter.convertClanMembers(testContent))) as JsonArray<String>


            Assert.assertEquals(43, testee.size)
        }

    }
})
