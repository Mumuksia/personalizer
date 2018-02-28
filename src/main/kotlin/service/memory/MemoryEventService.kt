package service.memory

import scheduller.data.Event
import scheduller.data.Scheduler
import service.PersistenceEventService

class MemoryEventService : PersistenceEventService {

    private val events: MutableMap<Long, Event> = HashMap()

    override fun updateEvent(event: Event) {
        if (events.containsKey(event.id)){
            events.replace(event.id, event)
        } else {
            events[event.id] = event
        }
    }

    override fun getEvent(eventId: Long) : Event {
        return events[eventId]!!
    }

    override fun getAllEvent(): List<Event> {
        return events.values.toList()
    }

    override fun getEventsForSchedulers(schedulers: List<Scheduler>) : List<Event>{
        return events.values.filter { event -> schedulers.any {scheduler -> scheduler.eventId == event.id }}
    }

}
