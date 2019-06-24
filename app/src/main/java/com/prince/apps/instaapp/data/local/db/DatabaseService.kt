package com.prince.apps.instaapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prince.apps.instaapp.data.local.db.dao.DummyDao
import com.prince.apps.instaapp.data.local.db.entity.DummyEntity
import javax.inject.Singleton

/**
 * Created by prince patel on 6/23/2019.
 */
@Singleton
@Database(
    entities = [DummyEntity::class],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {
    abstract fun dummyDao(): DummyDao
}
