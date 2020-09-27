package se.ctescape.roomtutorial.repository

import androidx.lifecycle.LiveData
import se.ctescape.roomtutorial.data.UserDao
import se.ctescape.roomtutorial.model.User

class UserRepository(private val userDao: UserDao) {
    val readAllData : LiveData<List<User>> = userDao.readAllData()
    suspend fun addUser(user : User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
}