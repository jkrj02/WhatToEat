package com.jkrj02.whattoeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jkrj02.whattoeat.databinding.ActivityMainBinding
import com.jkrj02.whattoeat.eatPackage.AddFragment
import com.jkrj02.whattoeat.eatPackage.ListFragment
import com.jkrj02.whattoeat.eatPackage.UpdateFragment
import com.jkrj02.whattoeat.foodPackage.FoodListFragment

class MainActivity : AppCompatActivity(),
    AddFragment.HideNavigation,
    UseFragment.HideNavigation,
    HomeFragment.HideNavigation,
    ListFragment.HideNavigation,
    UpdateFragment.HideNavigation,
    FoodListFragment.HideNavigation{
    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController
    private var numberOfEat = 0
    private var numberOfFood = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //不显示标题栏
//        supportActionBar?.hide()
        //数据绑定
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //开启导航
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.myNavHost) as NavHostFragment
        navController=navHostFragment.navController
//        navController = findNavController(R.id.myHomeFragment)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setupWithNavController(navController)
//        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()||navController.navigateUp()
    }

    override fun hide() {
        binding.bottomNavigation.visibility = View.INVISIBLE
    }

    override fun undo() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    override fun getNumberOfEat(): Int {
        return numberOfEat
    }

    override fun getNumberOfFood(): Int {
        return numberOfFood
    }

    override fun setNumberOfEat(num: Int) {
        numberOfEat = num
    }

    override fun setNumberOfFood(num: Int) {
        numberOfFood = num
    }

}