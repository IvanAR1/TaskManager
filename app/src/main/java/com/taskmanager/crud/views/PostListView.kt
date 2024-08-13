package com.taskmanager.crud.views
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.taskmanager.crud.data.models.posts.Data
import com.taskmanager.crud.data.models.posts.PostAdd.PostAdd
import com.taskmanager.crud.data.models.posts.PostResource
import com.taskmanager.crud.data.requests.PostAddRequest
import com.taskmanager.crud.data.services.PostServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostListView: ViewModel() {
    var postsResources:ArrayList<Data> by mutableStateOf(arrayListOf())

    fun getPosts(){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val posts = PostServiceFactory.makePostService();
                val response = posts.getPosts(1, 10)
                withContext(Dispatchers.Main){
                    postsResources = response.data as ArrayList<Data>
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun addPost(postRequest: PostAddRequest){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val posts = PostServiceFactory.makePostService();
                val response: PostAdd = posts.addPost(postRequest)
                withContext(Dispatchers.Main){
                    if(response.code == 200){
                        getPosts()
                    }
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun updatePost(slug:String, postRequest: PostAddRequest){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val posts = PostServiceFactory.makePostService();
                val response: PostAdd = posts.updatePost(slug=slug, postRequest)
                withContext(Dispatchers.Main){
                    if(response.code == 200){
                        getPosts()
                    }
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun deletePost(slug:String){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val posts = PostServiceFactory.makePostService();
                val response: PostAdd = posts.deletePost(slug=slug)
                withContext(Dispatchers.Main){
                    if(response.code == 200){
                        getPosts()
                    }
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}