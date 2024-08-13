package com.taskmanager.crud.data.services
import com.taskmanager.crud.Constants
import com.taskmanager.crud.data.models.posts.Data
import com.taskmanager.crud.data.models.posts.PostAdd.PostAdd
import com.taskmanager.crud.data.models.posts.PostResource
import com.taskmanager.crud.data.requests.PostAddRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface PostService {
    @GET ("posts")
    suspend fun getPosts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): PostResource

    @GET ("posts/{slug}")
    suspend fun getPost(
        @Query("slug") slug: String
    ): Data

    @POST ("posts")
    suspend fun addPost(
        @Body postRequest: PostAddRequest
    ): PostAdd

    @PUT ("posts/{slug}")
    suspend fun updatePost(
        @Path("slug") slug: String,
        @Body postRequest: PostAddRequest
    ): PostAdd

    @DELETE ("posts/{slug}")
    suspend fun deletePost(
        @Path("slug") slug: String
    ): PostAdd
}

object PostServiceFactory {
    fun makePostService(): PostService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PostService::class.java)
    }
}