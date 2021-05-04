package com.company.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.io.*

class LyricsActivity : AppCompatActivity() {
    private lateinit var lyrics: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics)



        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        var music = sharedPreference.getInt("music",1)

        if(music == 1){
            play(R.raw.qw, 1)
        }
        else if(music == 2){
            play(R.raw.qwe, 2)
        }
        else  if(music == 3){
            play(R.raw.qwer, 3)
        }
        else if(music==4){
            play(R.raw.qwert, 4)
        }
        else{
            play(R.raw.qwerty, 5)
        }

    }

    fun play(id: Int, shp: Int){
        lyrics = findViewById(R.id.lyyr)


        var string: String? = ""
        val stringBuilder = StringBuilder()
        val `is`: InputStream = this.resources.openRawResource(id)
        val reader = BufferedReader(InputStreamReader(`is`))
        while (true) {
            try {
                if (reader.readLine().also { string = it } == null) break
            } catch (e: IOException) {
                e.printStackTrace()
            }
            stringBuilder.append(string).append("\n")
            lyrics.text = stringBuilder
        }
        `is`.close()
    }
}