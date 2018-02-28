package service

import scheduller.data.Scheduler

interface PersistenceSchedulerService {

    fun updateScheduler(scheduler: Scheduler)

    fun getScheduler(schedulerId: Long): Scheduler

    fun getAllSchedulers(): List<Scheduler>
}
