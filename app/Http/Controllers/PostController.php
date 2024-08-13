<?php

namespace App\Http\Controllers;

use App\Models;
use App\Http\Requests\StorePostRequest;
use App\Http\Requests\UpdatePostRequest;

class PostController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(StorePostRequest $request, $active = true)
    {
        if($active)
            $posts = Models\Post::where('is_active', '=', 1)->paginate($request->input('per_page', 10));
        else
            $posts = Models\Post::paginate($request->input('per_page', 10));
        return response()->json($posts);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StorePostRequest $request)
    {
        $petition = $request->all();
        $post = new Models\Post($petition);
        $post->title = $petition['title'];
        $post->description = $petition['description'];
        $post->slug = $petition['slug'];
        $post->user_id = $petition['user_id'];
        if($post->save()) {
            $response = [
                'message' => 'Post created',
                'post' => $post,
                'code' => 201
            ];
        }else{
            $response = [
                'message' => 'Error creating post',
                'code' => 500
            ];
        }
        return response()->json($response, $response['code']);
    }

    /**
     * Display the specified resource.
     */
    public function show(Models\Post $post)
    {
        return response()->json($post);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdatePostRequest $request, Models\Post $post)
    {
        $petition = $request->all();
        $post->title = $petition['title'];
        $post->description = $petition['description'];
        $post->slug = $petition['slug'];
        $post->user_id = $petition['user_id'];
        if($post->save()) {
            $response = [
                'message' => 'Post updated',
                'post' => $post,
                'code' => 200
            ];
        }else{
            $response = [
                'message' => 'Error updating post',
                'code' => 500
            ];
        }
        return response()->json($response, $response['code']);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Models\Post $post)
    {
        if($post->delete()) {
            $response = [
                'message' => 'Post deleted',
                'code' => 200
            ];
        }else{
            $response = [
                'message' => 'Error deleting post',
                'code' => 500
            ];
        }
        return response()->json($response, $response['code']);
    }
}
