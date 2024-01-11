package com.example.myhouse.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myhouse.data.dtos.CameraDto
import com.example.myhouse.data.dtos.DoorDto

@Database(entities = [CameraDto::class, DoorDto::class], version = 1, exportSchema = true)
abstract class HouseDatabase : RoomDatabase() {
    abstract fun getCameraDao(): CameraDao
    abstract fun getDoorDao(): DoorDao
}