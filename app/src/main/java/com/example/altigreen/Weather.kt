package com.example.altigreen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Weather : AppCompatActivity() {
    val API_KEY = "c242d6d02cb0a069b91ebe3b37f0d282"
    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        var cityName = intent.getStringExtra("cityName").toString()
        findViewById<TextView>(R.id.cityNameView).text = cityName
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        findViewById<TextView>(R.id.dateTimeView).text = LocalDateTime.now().format(formatter)
        var bg = findViewById<ConstraintLayout>(R.id.ConstraintLayout_1)
//        var mVideoView = findViewById<VideoView>(R.id.videoView)
        Glide.with(this).load("https://i.ibb.co/rb4rrJL/26.png")
            .into(findViewById<ImageView>(R.id.mainIcon));
//        findViewById<ImageView>(R.id.bg).setBackgroundResource(R.raw.sun)

//        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sun))

        val api = RetrofitHelper.getInstance().create(WeatherAPI::class.java)
        var result: Response<WeatherData>? = null
        var coroutine = GlobalScope.launch(Dispatchers.Main) {
            result = api.getWeather(cityName, API_KEY, "metric")
            Log.d("bharath", result?.body()?.main?.temp.toString())
            findViewById<TextView>(R.id.tempView).text =
                result?.body()?.main?.temp.toString() + "°C"
            findViewById<TextView>(R.id.cityNameView).text = result?.body()?.name.toString()
            findViewById<TextView>(R.id.discriptionView).text =
                result?.body()?.weather?.get(0)?.main.toString()
            findViewById<TextView>(R.id.humidityView).text =
                result?.body()?.main?.humidity.toString() + "%"
            findViewById<TextView>(R.id.pressureView).text =
                result?.body()?.main?.pressure.toString() + " hPa"
            findViewById<TextView>(R.id.windSpeedView).text =
                result?.body()?.wind?.speed.toString() + " m/s"
            findViewById<TextView>(R.id.feelsLikeView).text =
                "Feels Like " + result?.body()?.main?.feelsLike.toString() + "°C"

            var condition = result?.body()?.weather?.get(0)?.main.toString()
            Log.d("bharath", condition)
            if (condition == "Clouds") {
                Glide.with(applicationContext).load("https://i.ibb.co/PZQXH8V/27.png")
                    .into(findViewById<ImageView>(R.id.mainIcon));
//                findViewById<ImageView>(R.id.bg).setBackgroundResource(R.raw.cloud)
//                mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cloud))
            } else if (condition == "Rain") {
                Glide.with(applicationContext).load("https://i.ibb.co/kBd2NTS/39.png")
                    .into(findViewById<ImageView>(R.id.mainIcon));
//                findViewById<ImageView>(R.id.bg).setBackgroundResource(R.raw.rain)
//                mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.rain))
            }
            else if (condition == "Thunderstorm") {
                Glide.with(applicationContext).load("https://i.ibb.co/kBd2NTS/39.png")
                    .into(findViewById<ImageView>(R.id.mainIcon));
//                findViewById<ImageView>(R.id.bg).setBackgroundResource(R.raw.rain)
//                mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.rain))
            }else if (condition == "Clear") {
                Glide.with(applicationContext).load("https://i.ibb.co/rb4rrJL/26.png")
                    .into(findViewById<ImageView>(R.id.mainIcon));
//                findViewById<ImageView>(R.id.bg).setBackgroundResource(R.raw.sun)
//            mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sun))
            } else if (condition == "Snow") {
                Glide.with(applicationContext).load("https://i.ibb.co/kBd2NTS/39.png")
                    .into(findViewById<ImageView>(R.id.mainIcon));
//                findViewById<ImageView>(R.id.bg).setBackgroundResource(R.raw.snow)
//            mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.snow))
            } else if (condition == "Haze") {
                Glide.with(applicationContext).load("https://i.ibb.co/PZQXH8V/27.png")
                    .into(findViewById<ImageView>(R.id.mainIcon));
//                findViewById<ImageView>(R.id.bg).setBackgroundResource(R.raw.haze)
//            mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.haze))
            }
        }
    }
}