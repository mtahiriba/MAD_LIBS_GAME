package com.example.lab_05_activity1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lab_05_activity1.Result
import java.util.Scanner

class Words : AppCompatActivity() {

    lateinit var text:TextView
    lateinit var plan: EditText
    lateinit var wordText: TextView
    var index = 0
    var remainingwords = 0
    var storyindex = 0
    val stories = ArrayList<Int>()
    lateinit var arrayList: ArrayList<String>
    lateinit var arrayResult:ArrayList<String>


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)


        stories.add(R.raw.madlib0)
        stories.add(R.raw.madlib1)
        stories.add(R.raw.madlib2)
        stories.add(R.raw.madlib3)
        stories.add(R.raw.madlib4)

        storyindex = (0..4).random()

        text = findViewById(R.id.textView7)
        plan = findViewById(R.id.editTextTextPersonName)
        wordText = findViewById(R.id.nowords)

        arrayList = ArrayList<String>()
        arrayResult = ArrayList<String>()

        val scan: Scanner = Scanner(resources.openRawResource(stories[storyindex]))
        var check = false
        var word = ""
        while(scan.hasNext()){
            for (i in scan.nextLine()){
                if(i == '<'){
                    check = true
                    continue
                }
                else if(i == '>') {
                    check = false
                    arrayList.add(word)
                    word = ""
                }
                if(check)
                    word +=i
            }
        }

        plan.hint = arrayList[index]
        remainingwords = arrayList.size
        wordText.text = remainingwords.toString() + " word(s) Left"
    }

    fun okClick(view: View){
        if(plan.text.toString() != ""){
            arrayResult.add(plan.text.toString())
            index++
            if(index < arrayList.size){
                plan.setText("")
                plan.hint = arrayList[index]
            }
            remainingwords--
            if(remainingwords == 0){
                val args:Bundle = Bundle()
                args.putSerializable("ArrayList", arrayResult)
                val intent: Intent = Intent(this, Result::class.java)
                intent.putExtra("resultData", args)
                intent.putExtra("storyvalue", stories[storyindex])
                startActivity(intent)

            }
            wordText.text = remainingwords.toString() + " word(s) Left"
        }
        else
            Toast.makeText(this, "Please fill "+arrayList[index], Toast.LENGTH_LONG).show()
    }
}