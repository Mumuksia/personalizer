package fanta.service;

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.io.File
import java.nio.file.Paths


@RunWith(JUnitPlatform::class)
class MantraPLRoundsConverterTest : Spek({
    describe("convert pl round string to objects") {
        val mantraParser = MantraFutMobParser()

        println(Paths.get("").toAbsolutePath().toString())
        it("response should have all matches") {
            val testContent = File(Paths.get("").toAbsolutePath().toString() +
                    "/src/test/resources/mantra/pl1920GW1.json").readText()
            val testee = mantraParser.convertMatchPLRounds(testContent)


            Assert.assertEquals(10, testee.size)
        }

    }
})