
package com.vocalsplitter.ai

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.rgb(15, 15, 25)
        window.navigationBarColor = Color.rgb(15, 15, 25)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 35, 28, 35)
            setBackgroundColor(Color.rgb(15, 15, 25))
        }

        val title = TextView(this).apply {
            text = "VOCAL SPLITTER AI"
            textSize = 27f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "Separação de áudio inteligente"
            textSize = 15f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        status = TextView(this).apply {
            text = "Escolha uma música para começar"
            textSize = 16f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(10, 35, 10, 35)
        }

        val selectButton = Button(this).apply {
            text = "ESCOLHER MÚSICA"
            setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    type = "audio/*"
                    addCategory(Intent.CATEGORY_OPENABLE)
                }
                startActivityForResult(intent, 100)
            }
        }

        val vocalButton = Button(this).apply {
            text = "ISOLAR VOZ"
            isEnabled = false
        }

        val instrumentalButton = Button(this).apply {
            text = "ISOLAR INSTRUMENTAL"
            isEnabled = false
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(status)
        layout.addView(selectButton)
        layout.addView(vocalButton)
        layout.addView(instrumentalButton)

        setContentView(layout)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            val uri: Uri? = data?.data
            if (uri != null) {
                status.text = "Música selecionada!\n$uri"
            }
        }
    }
}
