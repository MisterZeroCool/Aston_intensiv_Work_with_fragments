package ru.agavrilyuk.aston_intensiv_work_with_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.agavrilyuk.aston_intensiv_work_with_fragments.main_screen.MainScreenFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction().apply {
                replace(R.id.host, MainScreenFragment())
            }.commit()
    }
}