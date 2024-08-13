<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Casts\Attribute;
use Illuminate\Support\Str;
use Carbon\Carbon;

class Post extends Model
{
    use HasFactory;

    protected $fillable = [
        'title',
        'description',
        'slug',
        'is_active',
        'user_id',
    ];

     /* Casting */
     protected function casts(){
        return [
            'title' => 'string',
            'description' => 'string',
            'category' => 'string',
            'created_at' => 'date',
            'updated_at' => 'date',
        ];
    }

    protected $guarded = [
        'is_active',
    ];

    public function user()
    {
        return $this->belongsTo(User::class);
    }

    /* Mutadores y accesores */
    protected function title():Attribute{
        return Attribute::make(
            set: fn($value) => strtolower($value),
            get: fn($value) => Str::title($value),
        );
    }

    protected function getCreatedAtAttribute($date){
        return Carbon::parse($date)->format('d-m-Y');
    }

    protected function getUpdatedAtAttribute($date){
        return Carbon::parse($date)->format('d-m-Y');
    }

    public function getRouteKeyName(){
        return 'slug';
    }
}
