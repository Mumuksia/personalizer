package scheduller.service

import scheduller.data.Event
import scheduller.data.Scheduler
import service.PersistenceEventService
import service.PersistenceSchedulerService
import service.memory.MemoryEventService
import service.memory.MemorySchedulerService
import java.time.Instant

class CheckSchedulerEventsService {

    val schedulerService: PersistenceSchedulerService = MemorySchedulerService()
    val eventService: PersistenceEventService = MemoryEventService()

    fun getAllEventsToExecute(): List<Event> {
        val schedulers = schedulerService.getAllSchedulers()
        return getAllEventsForSchedulers(schedulers.filter{s -> s.alarmTime.isBefore(Instant.now())})
    }

    fun getAllEventsForSchedulers(schedulers: List<Scheduler>): List<Event> {
        return eventService.getEventsForSchedulers(schedulers)
    }
}
