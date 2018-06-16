package scheduler

import controller.EventController
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class AddEvent : Spek({
    describe("event controller") {
        val eventController = EventController()

        it("database should contain created event") {
            eventController.addEvent("name", 2, "description", "12")

            assertEquals(1, eventController.getAll().size)
        }

    }
})