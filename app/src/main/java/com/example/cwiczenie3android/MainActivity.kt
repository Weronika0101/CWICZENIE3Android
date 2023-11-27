package com.example.cwiczenie3android

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.cwiczenie3android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyTheme()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.sfcontainer) as NavHostFragment
        val navController = navHostFragment.navController


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigationView.setupWithNavController(navController)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_comeback ->{
                setPrefsTheme(0)
                recreate()
                true
            }
           R.id.action_option1 -> {
                setPrefsTheme(1)
                recreate()
                return true;
            }
            R.id.action_option2 -> {
                setPrefsTheme(2)
                recreate()
                return true;
            }
            R.id.action_option3 -> {
                setPrefsTheme(3)
                recreate()
                return true;
            }
//            R.id.ltext -> {
//                setPrefsFont(1)
//                recreate()
//                return true;
//            }
//            R.id.stext -> {
//                setPrefsFont(2)
//                recreate()
//                return true;
//            }
            R.id.reset -> {
                setPrefsFont(0)
                recreate()
                true
            }
            else -> {

                super.onOptionsItemSelected(item)
            }

        }
    }

    private fun setPrefsTheme(themeNum: Int) {
        val data : SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

        val editor = data.edit()
        editor.putInt("theme_num",themeNum)
        editor.apply()
    }


    private fun setPrefsFont(fontNum: Int) {
        val data : SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

        val editor = data.edit()
        editor.putInt("font_num", fontNum)
        editor.apply()
    }

    private fun applyTheme() {
        val data = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        var themeNum = data.getInt("theme_num",0)
        var fontNum = data.getInt("font_num",0)
        when(themeNum) {
            1-> setTheme(R.style.AppTheme1)
            2-> setTheme(R.style.AppTheme2)
            3-> setTheme(R.style.AppTheme3)
            4->setTheme(R.style.SmallText)
            else-> setTheme(R.style.Theme_CWICZENIE3Android)
        }
        when(fontNum) {
            1-> theme.applyStyle(R.style.SmallText,true)
            2-> setTheme(R.style.LargeText)
            else-> setTheme(R.style.NormalText)
        }
    }
}