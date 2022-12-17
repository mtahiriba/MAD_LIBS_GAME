package com.example.lab_05_activity1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class Result : AppCompatActivity() {

    lateinit var text: TextView
    var index = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent = getIntent()
        val args: Bundle? = intent.getBundleExtra("resultData")
        val arrayResult:ArrayList<String> = args?.getSerializable("ArrayList") as ArrayList<String>
        var story = intent.getIntExtra("storyvalue", 0)
        text = findViewById(R.id.textresult)

        val scan: Scanner = Scanner(resources.openRawResource(story))
        var check = false
        var data = ""
        while(scan.hasNext()){
            for(i in scan.nextLine()){
                if(i == '<')
                    check = true
                else if(i == '>') {
                    data += arrayResult[index++]
                    check = false
                    continue
                }
                if(check)
                    continue
                else
                    data += i
            }
            data += '\n'
        }
        text.text = data
    }

    fun otherStory(view: View){
        val intent:Intent = Intent(this, Words::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}