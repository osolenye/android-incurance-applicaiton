package com.example.finalproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.finalproject.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide the ActionBar
        supportActionBar?.hide()


        // Получаем NavigationView
        val navigationView = binding.navMenu

        // Получаем заголовок навигационного меню
        val headerView = navigationView.getHeaderView(0)

        // Находим кнопку внутри заголовка
        val button = headerView.findViewById<Button>(R.id.header_button)

        val button_open_navigationView = binding.headerButton;
        button_open_navigationView.setOnClickListener {
            navigationView.visibility = View.VISIBLE;
        }


        // Устанавливаем слушатель на кнопку
        button.setOnClickListener {
            navigationView.visibility = View.GONE;
        }





//        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
        navController.navigate(R.id.policyBuyFragment);


        // Устанавливаем слушатель для обработки нажатий на элементы меню
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Обрабатываем нажатия на элементы меню
            when (menuItem.itemId) {
                R.id.nav_policies -> {
                    // Действия для "Покупка полисов"
                    navigationView.visibility = View.GONE;
                    navController.navigate(R.id.policyBuyFragment)
                    true
                }
                R.id.nav_requests -> {
                    // Действия для "Заявки на выплату"
                    navigationView.visibility = View.GONE;
                    navController.navigate(R.id.requestOptionFragment)
                    true
                }
                R.id.nav_profile -> {
                    // Действия для "Профиль"
                    navigationView.visibility = View.GONE;
                    navController.navigate(R.id.profileFragment)
                    true
                }
                R.id.nav_logout -> {
                    // Действия для "Выход из аккаунта"
                    navigationView.visibility = View.GONE;
                    navController.navigate(R.id.loginFragment)
                    true
                }
                else -> false
            }
        }
    }
}


