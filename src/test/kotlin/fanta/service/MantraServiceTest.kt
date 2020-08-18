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
class MantraServiceTest : Spek({
    describe("get json representation of a round") {
        val mantraService = MantraFutMobService()

        println(Paths.get("").toAbsolutePath().toString())
        it("response should not be null") {
            val testContent = File(Paths.get("").toAbsolutePath().toString() +
                    "/src/test/resources/mantra/pl1920.json").readText()
            val testee = mantraService.getJsonStringForRound(2)


            Assert.assertNotNull(testee)
        }

    }
})