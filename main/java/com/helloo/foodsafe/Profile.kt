package com.helloo.foodsafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val camera = findViewById<Button>(R.id.camera)



        val prefs = {}

        val milk = false
        val eggs = false
        val peanuts = false
        val treeNuts = false
        val fish = false
        val shellfish = false
        val soy = false
        val wheat = false

        val milkCheck = findViewById<CheckBox>(R.id.milk)
        val eggsCheck = findViewById<CheckBox>(R.id.egg)
        val peanutsCheck = findViewById<CheckBox>(R.id.peanut)
        val treeNutsCheck = findViewById<CheckBox>(R.id.treenut)
        val fishCheck = findViewById<CheckBox>(R.id.fish)
        val shellfishCheck = findViewById<CheckBox>(R.id.shellfish)
        val soyCheck = findViewById<CheckBox>(R.id.soy)
        val wheatCheck = findViewById<CheckBox>(R.id.wheat)

        val updateBtn = findViewById<Button>(R.id.update)
        updateBtn.setOnClickListener {
            val map: HashMap<String, Boolean> = hashMapOf("milk" to milkCheck.isChecked, "eggs" to eggsCheck.isChecked, "peanuts" to peanutsCheck.isChecked, "treeNuts" to treeNutsCheck.isChecked, "fish" to fishCheck.isChecked, "shellfish" to shellfishCheck.isChecked, "soyCheck" to soyCheck.isChecked, "wheat" to wheatCheck.isChecked)
        }

        camera.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("milk", milkCheck.isChecked)
            intent.putExtra("eggs", eggsCheck.isChecked)
            intent.putExtra("peanuts", peanutsCheck.isChecked)
            intent.putExtra("treeNuts", treeNutsCheck.isChecked)
            intent.putExtra("fish", fishCheck.isChecked)
            intent.putExtra("shellfish", shellfishCheck.isChecked)
            intent.putExtra("soy", soyCheck.isChecked)
            intent.putExtra("wheat", wheatCheck.isChecked)
            Log.d("random", eggsCheck.isChecked.toString())

            startActivity(intent)
        }
    }
}