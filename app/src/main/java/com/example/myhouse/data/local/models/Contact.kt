package com.example.myhouse.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String? = null,
    val surName: String,
    val number: String? = null,
    val icon: String? = null
)