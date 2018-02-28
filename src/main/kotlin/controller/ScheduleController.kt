package controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import scheduller.data.Scheduler
import service.PersistenceSchedulerService
import service.memory.MemorySchedulerService
import java.time.Instant
import java.util.concurrent.atomic.AtomicLong

@RestController
class ScheduleController {

    val persistenceService: PersistenceSchedulerService = MemorySchedulerService()

    val counter = AtomicLong()

    @GetMapping("/scheduler/get")
    fun getScheduledEvent(@RequestParam(value = "schedulerId") schedulerId: String) : Scheduler =
            persistenceService.getScheduler(schedulerId.toLong())

    @GetMapping("/scheduler/add")
    fun addSchedulerEvent(@RequestParam(value = "eventId") eventId: String,
                          @RequestParam(value = "alarmTime") alarmTime: Instant,
                          @RequestParam(value = "repeatTimes") repeatTimes: Int,
                          @RequestParam(value = "repeatInterval") repeatInterval: Long)  =
            persistenceService.updateScheduler(Scheduler(counter.incrementAndGet(), eventId.toLong(), alarmTime, repeatTimes, repeatInterval))

    @GetMapping("/scheduler/get/all")
    fun getAll() : List<Scheduler>  =
            persistenceService.getAllSchedulers()

}
