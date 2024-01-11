package com.example.myhouse.data.dtos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myhouse.domain.CameraModel
import com.example.myhouse.utils.Constants.EMPTY_STRING
import com.google.gson.annotations.SerializedName


data class CamerasDto(
    val data: Data,
    val success: Boolean
)

data class Data(
    val cameras: List<CameraDto>,
    val room: List<String>
)

@Entity(tableName = "camera_table")
data class CameraDto(
    @PrimaryKey
    val id: Long,
    val favorites: Boolean,
    val name: String,
    val rec: Boolean,
    val room: String = EMPTY_STRING,
    @SerializedName("snapshot")
    val image: String
)

fun CameraDto.toDomainModel() =
    CameraModel(id, favorites, name, rec, room ?: EMPTY_STRING, image)

fun CameraModel.toDataDto() = CameraDto(id, favorites, name, rec, room, image)

fun List<CameraDto>.toDomainModel() = this.map { it.toDomainModel() }

fun List<CameraModel>.toDataDto() = this.map { it.toDataDto() }