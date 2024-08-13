package com.example.crud_retrofit_jetpack_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.taskmanager.crud.data.models.posts.Data
import com.taskmanager.crud.views.PostListView

@Composable
fun CardPosts(
    funId: (Int) -> Unit,
    funTitle: (String) -> Unit,
    funDescription: (String) -> Unit,
    funSlug: (String) -> Unit,
    funOldSlug: (String) -> Unit = { _ -> },
    fnDeletePost: (String) -> Unit,
    post: Data,
    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            Arrangement.Center
        ) {
            Text(
                text = "ID: ${post.id}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Title: ${post.title}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Description: ${post.description}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Slug: ${post.slug}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Is Active: ${post.is_active}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "User ID: ${post.user_id}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f),
                    onClick = {
                        funId(post.id)
                        funTitle(post.title)
                        funDescription(post.description)
                        funSlug(post.slug)
                        funOldSlug(post.slug)
                    }
                ) {
                    Text(
                        text = "Editar"
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f),
                    onClick = {
                        fnDeletePost(post.slug)
                    }
                ) {
                    Text(
                        text = "Borrar"
                    )
                }
            }
        }
    }
}