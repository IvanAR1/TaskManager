<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers;

Route::apiResource('posts', Controllers\PostController::class);
