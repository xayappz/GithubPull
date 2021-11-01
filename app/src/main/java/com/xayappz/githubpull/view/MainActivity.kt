package com.xayappz.githubpull.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xayappz.githubpull.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gitOwnerName = findViewById<EditText>(R.id.gitownered)
        val gitRepoName = findViewById<EditText>(R.id.gitownerepoed)

        val button = findViewById<Button>(R.id.fetchButton)


        button.setOnClickListener {


            if (gitOwnerName.text.isEmpty() || gitRepoName.text.isEmpty()) {
                Toast.makeText(
                    this,
                    "Both fields are required ",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(this, SearchResultActivity::class.java)
                intent.putExtra(
                    "owner",
                    gitOwnerName.text.toString().trim()
                )
                intent.putExtra(
                    "repo",
                    gitRepoName.text.toString().trim()
                )

                startActivity(intent)
            }

        }
    }
}
