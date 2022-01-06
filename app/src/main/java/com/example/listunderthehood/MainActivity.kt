package com.example.listunderthehood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var loadButton : Button
    private lateinit var linearLayout : LinearLayout
    private lateinit var laptops: ArrayList<Laptop>
    private val TAG = "MainLifeCycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        laptops = ArrayList()

        for (i in 0..3){
            laptops.add(Laptop(R.drawable.img_asus, "Asus tuf gaming", "Geared for serious gaming and real-world durability, the TUF Gaming A15 is a fully-loaded Windows 10 Pro gaming laptop that can carry you to victory. Powered by the latest AMD Ryzen™ 9 CPU and GeForce RTX™ 2060 GPU, action-packed gameplay is fast, fluid, and fully saturates speedy IPS-level displays up to 144Hz"))
        }

        initViews()

        if(savedInstanceState != null){
            val savedLaptops = savedInstanceState.getParcelableArrayList<Laptop>("laptops")

            savedLaptops!!.forEach{
                linearLayout.addView(addItem(it))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("laptops", laptops)
    }

    private fun initViews(){

        linearLayout = findViewById(R.id.container_root)
        loadButton = findViewById(R.id.btn_load)

        loadButton.setOnClickListener {
            for (laptop in laptops){
                linearLayout.addView(addItem(laptop))
            }
        }

    }
    private fun addItem(laptop: Laptop) : LinearLayout{
        val tempLinearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            val parms = LinearLayout.LayoutParams(-1, -2)
            parms.setMargins(50,50,50,50)
            background = getDrawable(R.drawable.rounded_backgroung)

            layoutParams = parms
        }

        val imageView = ImageView(this).apply {
            val parms = LinearLayout.LayoutParams(-1,350)
            parms.setMargins(50,50,50,50)
            setImageResource(laptop.image)
            layoutParams = parms
        }

        val titleTextView = TextView(this).apply {
            text = laptop.title
            val parms = LinearLayout.LayoutParams(-1, -2)
            parms.setMargins(50,50,50,50)
            textSize = 20f
            layoutParams = parms
        }

        val descTextView = TextView(this).apply {
            val parms = LinearLayout.LayoutParams(-1, -2)
            parms.setMargins(50,50,50,50)
            layoutParams = parms
            text = laptop.description
        }

        tempLinearLayout.addView(imageView)
        tempLinearLayout.addView(titleTextView)
        tempLinearLayout.addView(descTextView)

        return tempLinearLayout
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"OnStart")
    }
}