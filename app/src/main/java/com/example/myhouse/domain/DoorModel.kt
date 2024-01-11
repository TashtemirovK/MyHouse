package com.example.myhouse.domain

data class DoorModel(
    val id: Long,
    val favorites: Boolean,
    val name: String,
    val room: String,
    val image: String
)
