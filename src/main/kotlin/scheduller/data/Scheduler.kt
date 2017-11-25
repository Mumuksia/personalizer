package scheduller.data

import java.time.Instant

data class Scheduler(val id: Long, val eventId: Long, val alarmTime: Instant, val repeatTimes: Int, val repeatInterval: Long)
