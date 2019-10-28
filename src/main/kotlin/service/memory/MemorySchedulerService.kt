package service.memory

import scheduller.data.Scheduler
import service.PersistenceSchedulerService

class MemorySchedulerService : PersistenceSchedulerService {

    private val schedulers: MutableMap<Long, Scheduler> = HashMap()


    override fun updateScheduler(scheduler: Scheduler) {
        if (schedulers.containsKey(scheduler.id)){
            schedulers.replace(scheduler.id, scheduler)
        } else {
            schedulers.put(scheduler.id, scheduler)
        }
    }

    override fun getScheduler(schedulerId: Long): Scheduler {
        return schedulers[schedulerId]!!
    }

    override fun getAllSchedulers(): List<Scheduler> {
        //println("getting all schedulers")
        return schedulers.values.toList()
    }
}
