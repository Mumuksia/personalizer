package controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import scheduller.data.Event
import service.PersistenceEventService
import service.memory.MemoryEventService
import java.util.concurrent.atomic.AtomicLong

@RestController
class EventController {

    val persistenceService: PersistenceEventService = MemoryEventService()

    val counter = AtomicLong()

    @GetMapping("/events/get")
    fun getEvent(@RequestParam(value = "eventId") eventId: String) : Event =
            persistenceService.getEvent(eventId.toLong())

    @GetMapping("/events/add")
    fun addEvent(@RequestParam(value = "name") name: String,
                 @RequestParam(value = "executedTimes") executedTimes: Int,
                 @RequestParam(value = "description") description: String,
                 @RequestParam(value = "userId") userId: String)  =
            persistenceService.updateEvent(Event(counter.incrementAndGet(), name, userId.toLong(), description, executedTimes))

    @GetMapping("events/get/all")
    fun getAll() : List<Event>  =
            persistenceService.getAllEvent()

}
