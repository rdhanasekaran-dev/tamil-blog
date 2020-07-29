package com.dogood.tamilblogs.data

import java.util.*

data class DicoverMovie(
    val id:Int,
    val video:Boolean,
    val vote_count:Int,
    val vote_average: Float,
    val title: String,
    val release_date:String,
    val original_language:String,
    val original_title:String,
    val genre_ids:Array<Int>,
    val backdrop_path: String,
    val adult:Boolean,
    val overview:String,
    val poster_path: String,
    val popularity:Float,
    val media_type: String
)