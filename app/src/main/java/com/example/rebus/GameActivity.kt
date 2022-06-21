package com.example.rebus

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class GameActivity : AppCompatActivity() {

    var level:Int? = null
    var puzzleTextView:TextView? = null
    var textView:TextView? = null
    var lvlTextView:TextView? = null
    var listOfQ: Array<String>? = null
    private lateinit var prefs: SharedPreferences
    private val APP_TOP_SCORE = "score"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        level = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt(APP_TOP_SCORE, 0)
        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)

        var listOfAnswers = resources.getStringArray(R.array.Words)
        listOfQ = resources.getStringArray(R.array.Puzzey)

        var  editText:EditText = findViewById(R.id.answer)
        var button = findViewById<Button>(R.id.okButton)
        lvlTextView = findViewById(R.id.lvlTextView)
        textView = findViewById(R.id.textView)
        puzzleTextView = findViewById(R.id.answer)
        textView!!.text = listOfQ!![level!!]
        lvlTextView!!.text = (level!! +1 ).toString() + "/15"
        button.setOnClickListener(){
           if (editText.text.toString() == listOfAnswers[level!!]){
               nextPuzzle()
           }
            Log.d("level", level.toString())
        }
    }

    private fun nextPuzzle() {
        val editor = prefs.edit()
        if (level == 14){
            var intent = Intent(this@GameActivity,MainActivity::class.java)
            level = 0
            editor.putInt(APP_TOP_SCORE, level!!).apply()
            startActivity(intent)
            finishAffinity()
        }
        else {
            level = level?.plus(1)
            textView!!.text = listOfQ!! [level!!]
            lvlTextView!!.text = (level!! +1 ).toString() + "/15"
            editor.putInt(APP_TOP_SCORE, level!!).apply()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(this@GameActivity,MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
    fun skip(view: View){
        nextPuzzle()
    }

}