package com.company.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MyPlaylistActivity : AppCompatActivity() {

    private lateinit var music: TextView
    private lateinit var music2: TextView
    private lateinit var music3: TextView
    private lateinit var music4: TextView
    private lateinit var music5: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_playlist)


        music = findViewById(R.id.badliar)

        music.setOnClickListener {
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putInt("music", 1)
            editor.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        music2 = findViewById(R.id.cardigan)

        music2.setOnClickListener {
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putInt("music", 2)
            editor.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        music3 = findViewById(R.id.graveyard)

        music3.setOnClickListener {
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putInt("music", 3)
            editor.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        music4 = findViewById(R.id.myfuture)

            music4.setOnClickListener {
                val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
                var editor = sharedPreference.edit()
                editor.putInt("music", 4)
                editor.commit()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        music5 = findViewById(R.id.myway)

        music5.setOnClickListener {
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putInt("music", 5)
            editor.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}