package com.example.myhouse.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myhouse.data.dtos.CameraDto

interface CameraDao {
    @Dao
    interface CameraDao {

        @Query("SELECT * FROM camera_table")
        fun getAllCameras(): List<CameraDto>

        @Insert
        fun insertCamera(camera: CameraDto)

        @Insert
        fun insertAllCameras(cameras: List<CameraDto>)

        @Update
        fun updateCamera(camera: CameraDto)

        @Update
        fun updateAllCameras(cameras: List<CameraDto>)

        @Delete
        fun deleteCamera(camera: CameraDto)
    }
}