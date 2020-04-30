package rozha.service;

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
class ClashAPIConverterInfoTest : Spek({
    describe("convert clan info responses") {
        val clashAPIConverter = ClashAPIConverter()

        println(Paths.get("").toAbsolutePath().toString())
        it("response should have valid values") {
            val testContent = File(Paths.get("").toAbsolutePath().toString() +
                    "/src/test/resources/rozha/clanInfo.json").readText()
            val testee = Parser.default().parse(StringBuilder(clashAPIConverter.convertClanInfo(testContent)))


            Assert.assertNotEquals("", testee)
        }

    }
})