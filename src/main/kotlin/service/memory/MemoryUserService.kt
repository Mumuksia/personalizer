package service.memory

import scheduller.data.User
import service.PersistenceUserService

class MemoryUserService : PersistenceUserService {

    private val users: MutableMap<Long, User> = HashMap()

    override fun updateUser(user: User) {
        if (users.containsKey(user.id)){
            users.replace(user.id, user)
        } else {
            users.put(user.id, user)
        }
    }

    override fun getUsers(): List<User> {
        return users.values.toList()
    }

    override fun getUser(id: Long): User {
        return users[id]!!
    }
}
