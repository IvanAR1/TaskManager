package com.taskmanager.crud.data.models.posts.PostAdd

data class Post(
    val created_at: String,
    val description: String,
    val id: Int,
    val is_active: Boolean,
    val slug: String,
    val title: String,
    val updated_at: String,
    val user_id: Int
)