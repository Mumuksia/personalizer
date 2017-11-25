package service

import scheduller.data.User

interface PersistenceUserService {

    fun updateUser(user: User)

    fun getUsers() :  List<User>

    fun getUser(id: Long) : User
}
