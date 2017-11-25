package controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import scheduller.data.User
import service.PersistenceUserService
import service.memory.MemoryUserService
import java.util.concurrent.atomic.AtomicLong

@RestController
class SchedulerController {

    val persistenceService: PersistenceUserService = MemoryUserService()

    val counter = AtomicLong()

    @GetMapping("/scheduler/get")
    fun getUser(@RequestParam(value = "userId") userId: String) : User =
            persistenceService.getUser(userId.toLong())

    @GetMapping("/scheduler/add")
    fun addUser(@RequestParam(value = "name") name: String, @RequestParam(value = "email") email: String, @RequestParam(value = "lastName") lastName: String)  =
            persistenceService.updateUser(User(counter.incrementAndGet(), name, lastName, email))

    @GetMapping("/scheduler/get/all")
    fun getAll() : List<User>  =
            persistenceService.getUsers()

}
