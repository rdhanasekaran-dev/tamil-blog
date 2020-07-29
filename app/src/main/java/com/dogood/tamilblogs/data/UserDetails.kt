package com.dogood.tamilblogs.data

data class UserDetails(
    val id:Int,
    val iso_639_1:String,
    val iso_3166_1:String,
    val name:String,
    val include_adult:Boolean,
    val username:String
)
