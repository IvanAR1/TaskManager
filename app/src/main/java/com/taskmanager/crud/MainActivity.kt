package com.taskmanager.crud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crud_retrofit_jetpack_compose.CardPosts
import com.taskmanager.crud.data.models.posts.Data
import com.taskmanager.crud.ui.theme.TaskManagerTheme
import com.taskmanager.crud.views.FormPosts
import com.taskmanager.crud.views.PostListView
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.taskmanager.crud.data.models.posts.PostResource

class MainActivity : ComponentActivity() {
    private val viewPosts by viewModels<PostListView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ){
                        viewPosts.getPosts()
                        PostList(viewPosts.postsResources, viewPosts)
                    }
                }
            }
        }
    }
}

@Composable
fun PostList(listPosts: ArrayList<Data>, viewModel: PostListView) {
    var id by remember { mutableStateOf(0) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(1) }
    var slug by remember { mutableStateOf("") }
    var oldSlug by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            FormPosts(
                title = title,
                description = description,
                slug = slug,
                oldSlug = oldSlug,
                funTitle = { title = it },
                funDescription = { description = it },
                funSlug = { slug = it },
                id = id,
                viewModel = viewModel,
                funReset = {
                    id = 0
                    title = ""
                    description = ""
                    slug = ""
                    oldSlug = ""
                }
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(listPosts) { post ->
                        CardPosts(
                            funId = { id = it },
                            funTitle = { title = it },
                            funDescription = { description = it },
                            funSlug = { slug = it },
                            funOldSlug = { oldSlug = it },
                            post = post,
                            fnDeletePost = { viewModel.deletePost(it) }
                        )
                    }
                }
            }
        }
    }
}