package com.prince.apps.instaapp.data.repository

import com.prince.apps.instaapp.data.local.db.DatabaseService
import com.prince.apps.instaapp.data.local.prefs.UserPreferences
import com.prince.apps.instaapp.data.model.User
import com.prince.apps.instaapp.data.remote.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by prince patel on 6/23/2019.
 */
@Singleton
class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {

    fun saveCurrentUser(user: User) {
        userPreferences.setUserId(user.id)
        userPreferences.setUserName(user.name)
        userPreferences.setUserEmail(user.email)
        userPreferences.setAccessToken(user.accessToken)
    }

    fun removeCurrentUser() {
        userPreferences.removeUserId()
        userPreferences.removeUserName()
        userPreferences.removeUserEmail()
        userPreferences.removeAccessToken()
    }

    fun getCurrentUser(): User? {

        val userId = userPreferences.getUserId()
        val userName = userPreferences.getUserName()
        val userEmail = userPreferences.getUserEmail()
        val accessToken = userPreferences.getAccessToken()

        return if (userId !== null && userName != null && userEmail != null && accessToken != null)
            User(userId, userName, userEmail, accessToken)
        else
            null
    }
}