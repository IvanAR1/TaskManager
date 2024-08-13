package com.taskmanager.crud.data.requests

data class PostAddRequest(
    val title: String,
    val description: String,
    val slug: String,
    val is_active: Int = 1,
    val user_id: Int
)