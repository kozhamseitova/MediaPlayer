package com.company.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var mp: MediaPlayer
    private var totalTime: Int = 0
    private lateinit var positionBar: SeekBar
    private lateinit var volumeBar: SeekBar
    private lateinit var elapsedTimeLabel: TextView
    private lateinit var remainingTimeLabel: TextView
    private lateinit var playBtn: Button
    private lateinit var nextMusic: Button
    private lateinit var previousMusic: Button
    private lateinit var lyrics: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        var music = sharedPreference.getInt("music",1)

        if(music == 1){
            play(R.raw.badliarimaginedragons,1)
        }
        else if(music == 2){
            play(R.raw.cardigantaylorswift, 2)
        }
        else  if(music == 3){
            play(R.raw.graveyardhalsey, 3)
        }
        else if(music==4){
            play(R.raw.myfuturemileycyrus, 4)
        }
        else{
            play(R.raw.mywaycalvinharris, 5)
        }




    }

    fun play(id: Int, shp: Int){
        mp = MediaPlayer.create(this, id)
        mp.isLooping = true
        mp.setVolume(0.5f, 0.5f)
        totalTime = mp.duration

        nextMusic = findViewById(R.id.nextBtn)

        nextMusic.setOnClickListener {
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            if(shp==5){
                editor.putInt("music", 1)
            }
            else{
                editor.putInt("music", shp+1)
            }
            editor.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        previousMusic = findViewById(R.id.previousBtn)

        previousMusic.setOnClickListener {
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            if(shp == 1){
                editor.putInt("music", 5)
            }
            else{
                editor.putInt("music", shp-1)
            }
            editor.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        lyrics = findViewById(R.id.lyrics)

        lyrics.setOnClickListener {
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putInt("music", shp)
            editor.commit()
            val intent = Intent(this, LyricsActivity::class.java)
            startActivity(intent)
        }


        volumeBar = findViewById(R.id.volumeBar)
        positionBar = findViewById(R.id.positionBar)

        // Volume Bar
        volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        var volumeNum = progress / 100.0f
                        mp.setVolume(volumeNum, volumeNum)
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }
                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )

        // Position Bar
        positionBar.max = totalTime
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        mp.seekTo(progress)
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }
                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )

        // Thread
        Thread(Runnable {
            while (mp != null) {
                try {
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what

            // Update positionBar
            positionBar.progress = currentPosition

            // Update Labels
            var elapsedTime = createTimeLabel(currentPosition)
            elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel)
            elapsedTimeLabel.text = elapsedTime

            var remainingTime = createTimeLabel(totalTime - currentPosition)
            remainingTimeLabel = findViewById(R.id.remainingTimeLabel)
            remainingTimeLabel.text = "-$remainingTime"
        }
    }
    fun createTimeLabel(time: Int): String {
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }

    fun playBtnClick(v: View) {

        playBtn = findViewById(R.id.playBtn)
        if (mp.isPlaying) {
            // Stop
            mp.pause()
            playBtn.setBackgroundResource(R.drawable.play)

        } else {
            // Start
            mp.start()
            playBtn.setBackgroundResource(R.drawable.stop)
        }
    }
}