package scheduller.tasks

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import scheduller.service.CheckSchedulerEventsService

@Component
class EventTask {

    val checkSchedulerEventsService: CheckSchedulerEventsService = CheckSchedulerEventsService()

    @Scheduled(fixedRate = 60000)
    fun checkEventsToExecute(){
        println("checking for available events kotlin")
        val events = checkSchedulerEventsService.getAllEventsToExecute()
        events.forEach { event -> println(event.description) }
    }


}
