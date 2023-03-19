package com.example.altigreen

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") API_KEY: String,
        @Query("units") units: String
    ): Response<WeatherData>
}
