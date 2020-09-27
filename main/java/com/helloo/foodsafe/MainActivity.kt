package com.helloo.foodsafe

//import com.google.firebase.ml.vision.FirebaseVision
//import com.google.firebase.ml.vision.common.FirebaseVisionImage
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.wonderkiln.camerakit.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        camera.start()
    }

    override fun onPause() {
        super.onPause()
        camera.stop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val profileBtn = findViewById<Button>(R.id.profile)

        profileBtn.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val btn = findViewById<Button>(R.id.button)
        val camera = findViewById<com.wonderkiln.camerakit.CameraView>(R.id.camera)

        btn.setOnClickListener {
            camera.start()
            camera.captureImage()
        }

        camera.addCameraKitListener(object: CameraKitEventListener {
            override fun onVideo(p0: CameraKitVideo?) {
            }

            override fun onEvent(p0: CameraKitEvent?) {
            }

            override fun onImage(p0: CameraKitImage?) {
                var bitmap = p0!!.bitmap
                bitmap = Bitmap.createScaledBitmap(bitmap, camera.width, camera.height, false)
                camera.stop()

                findText(bitmap)
            }

            override fun onError(p0: CameraKitError?) {
            }

        })
    }

    private fun findText(bitmap: Bitmap?) {
        // Uses Cloud API

//        val image = FirebaseVisionImage.fromBitmap(bitmap!!)
//
//        val detector = FirebaseVision.getInstance().cloudTextRecognizer
//        val result = detector.processImage(image)
//            .addOnSuccessListener { firebaseVisionText ->
//                // Task completed successfully
//                // ...
//                Log.d("random", firebaseVisionText.text)
//                Toast.makeText(this, firebaseVisionText.text, Toast.LENGTH_LONG).show()
//            }
//            .addOnFailureListener { e ->
//                // Task failed with an exception
//                // ...
//                Log.d("random", "didn't work " + e.toString())
//            }

        // Uses Local ml-vision kit

        val milk = intent.extras!!.getBoolean("milk")
        val egg = intent.extras!!.getBoolean("eggs")
        val peanuts = intent.extras!!.getBoolean("peanuts")
        val treeNuts = intent.extras!!.getBoolean("treeNuts")
        val fish = intent.extras!!.getBoolean("fish")
        val shellfish = intent.extras!!.getBoolean("shellfish")
        val soy = intent.extras!!.getBoolean("soy")
        val wheat = intent.extras!!.getBoolean("wheat")
        val image = InputImage.fromBitmap(bitmap!!, 0)
        val recognizer = TextRecognition.getClient()

        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                // ...
                Log.d("random", visionText.text)
//                Toast.makeText(this, visionText.text, Toast.LENGTH_LONG).show()
                val carbregex= "Carb\\w*\\s+(\\d+)g".toRegex()
                val milkRegex = "MILK".toRegex()
                val eggRegex = "EGG".toRegex()
                val peanutRegex = "PEANUT".toRegex()
                val treeNutRegex = "TREE NUT".toRegex()
                val fishRegex = "FISH".toRegex()
                val shellfishRegex = "SHELLFISH".toRegex()
                val soyRegex = "SOY".toRegex()
                val wheatRegex = "WHEAT".toRegex()
                val match = carbregex.find(visionText.text)
//                val milkMatch = milkRegex.find(visionText.text)
//                Log.d("random", milkMatch?.value.toString())
                val grams = match?.value?.split(" ")
                val gramNo = grams?.get(1)?.split("g")

                Log.d("regex1", gramNo?.get(0).toString())
                val matchOneAfter = match?.range.toString().split("..")[1].toInt() + 1
                Log.d("regex", match?.range.toString().split("..")[1])
                Log.d("regex", match?.range.toString().get(7).toString())
                match?.value?.let { Log.d("regex", it) }
                Log.d("regex", match?.range.toString())
                Log.d("regex", "total carbs: " + visionText.text.get(matchOneAfter))
                Toast.makeText(applicationContext, "${gramNo?.get(0).toString()}g of carbs", Toast.LENGTH_SHORT).show()

                if (milk) {
                    val milkMatch = milkRegex.find(visionText.text)
                    if (milkMatch?.value != null) {
                        Toast.makeText(applicationContext, "MILK IS AN INGREDIENT \n${gramNo?.get(0).toString()}g of carbs" +
                                "", Toast.LENGTH_SHORT).show()
                    }
                }

                if (egg) {
                    val eggMatch = eggRegex.find(visionText.text)
                    if (eggMatch?.value != null) {
                        Toast.makeText(applicationContext, "EGG IS AN INGREDIENT \n" +
                                "${gramNo?.get(0).toString()}g of carbs", Toast.LENGTH_SHORT).show()
                    }
                }

                if (peanuts) {
                    val peanutMatch = peanutRegex.find(visionText.text)
                    Log.d("allergy", "yes peanut")
                    if (peanutMatch?.value != null) {
                        Toast.makeText(applicationContext, "PEANUTS ARE AN INGREDIENT \n" +
                                "${gramNo?.get(0).toString()}g of carbs", Toast.LENGTH_SHORT).show()
                    }
                }

                if (treeNuts) {
                    val treeNutsMatch = treeNutRegex.find(visionText.text)
                    if (treeNutsMatch?.value != null) {
                        Toast.makeText(applicationContext, "TREE NUTS ARE AN INGREDIENT \n" +
                                "${gramNo?.get(0).toString()}g of carbs", Toast.LENGTH_SHORT).show()
                    }
                }

                if (fish) {
                    val fishMatch = fishRegex.find(visionText.text)
                    if (fishMatch?.value != null) {
                        Toast.makeText(applicationContext, "FISH IS AN INGREDIENT \n" +
                                "${gramNo?.get(0).toString()}g of carbs", Toast.LENGTH_SHORT).show()
                    }
                }

                if (shellfish) {
                    val shellfishMatch = shellfishRegex.find(visionText.text)
                    if (shellfishMatch?.value != null) {
                        Toast.makeText(applicationContext, "SHELLFISH IS AN INGREDIENT \n" +
                                "${gramNo?.get(0).toString()}g of carbs", Toast.LENGTH_SHORT).show()
                    }
                }

                if (soy) {
                    val soyMatch = soyRegex.find(visionText.text)
                    if (soyMatch?.value != null) {
                        Toast.makeText(applicationContext, "SOY IS AN INGREDIENT \n" +
                                "${gramNo?.get(0).toString()}g of carbs", Toast.LENGTH_SHORT).show()
                    }
                }

                if (wheat) {
                    val wheatMatch = wheatRegex.find(visionText.text)
                    if (wheatMatch?.value != null) {
                        Toast.makeText(applicationContext, "WHEAT IS AN INGREDIENT  \n" +
                                "${gramNo?.get(0).toString()}g of carbs", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
                Log.d("random", "didn't work" + e.toString())
            }

    }
}