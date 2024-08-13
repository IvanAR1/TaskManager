package com.taskmanager.crud.data.models.posts

data class Data(
    val created_at: String,
    val description: String,
    val id: Int,
    val is_active: String,
    val slug: String,
    val title: String,
    val updated_at: String,
    val user_id: String
)