package service

import scheduller.data.Event
import scheduller.data.Scheduler

interface PersistenceEventService {

    fun updateEvent(event: Event)

    fun getEvent(eventId: Long): Event

    fun getAllEvent(): List<Event>

    fun getEventsForSchedulers(schedulers: List<Scheduler>): List<Event>
}
