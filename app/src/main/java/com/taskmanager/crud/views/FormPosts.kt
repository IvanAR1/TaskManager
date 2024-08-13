package com.taskmanager.crud.views

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.taskmanager.crud.R
import com.taskmanager.crud.data.requests.PostAddRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPosts(
    title: String,
    description: String,
    slug: String,
    userId: Int = 1,
    oldSlug: String = "",
    funTitle: (String) -> Unit,
    funDescription: (String) -> Unit,
    funSlug: (String) -> Unit,
    id: Int?,
    viewModel: PostListView,
    funReset: () -> Unit
) {
    Image(
        painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .alpha(0.5f)
    )
    OutlinedTextField(
        value = title,
        onValueChange = { funTitle(it) },
        label = { Text("Title") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
    OutlinedTextField(
        value = description,
        onValueChange = { funDescription(it) },
        label = { Text("Description") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
    OutlinedTextField(
        value = slug,
        onValueChange = { funSlug(it) },
        label = { Text("Slug") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
    Spacer(modifier = Modifier.padding(vertical = 8.dp))
    Button(modifier = Modifier.fillMaxWidth(),
        onClick = {
            if (id == 0) {
                viewModel.addPost(
                    PostAddRequest(
                        description = description,
                        slug = slug,
                        title = title,
                        user_id = userId
                    )
                )
            } else {
                viewModel.updatePost(
                    oldSlug,
                    PostAddRequest(
                        description = description,
                        slug = slug,
                        title = title,
                        user_id = userId
                    )
                )
            }
            funReset()
        }
    ) {
        Text(
            text = if (id == 0) "Add Post" else "Update Post",
        )
    }
}