package com.example.rebus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class GameActivity : AppCompatActivity() {

    var level = 0
    var puzzleTextView:TextView? = null
    var textView:TextView? = null
    var lvlTextView:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var listOfAnswers = resources.getStringArray(R.array.Words)
        var listOfQ = resources.getStringArray(R.array.Puzzey)

        var  editText:EditText = findViewById(R.id.answer)
        var button = findViewById<Button>(R.id.okButton)
        lvlTextView = findViewById(R.id.lvlTextView)
        textView = findViewById(R.id.textView)
        puzzleTextView = findViewById(R.id.answer)

        button.setOnClickListener(){
           if (editText.text.toString() == listOfAnswers[level]){
               nextPuzzle(listOfQ )
           }
            Log.d("level", level.toString())
        }
    }

    private fun nextPuzzle(listOfQ : Array<String>) {
        level += 1
        textView!!.text = listOfQ [level]
        lvlTextView!!.text = (level +1 ).toString() + "/15"
    }

}