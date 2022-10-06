package com.example.testcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.testcomponent.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
                    as NavHostFragment
        navController = navHostFragment.navController
        //lock swiped drawer navigation unless in startDestination
        navController.addOnDestinationChangedListener() { nc : NavController, nd : NavDestination, _ ->
         if (nd.id == nc.graph.startDestinationId) {
             binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
         }else {
             binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
         }
        }

        /*
        *configure the Navigation button to appear as an Up button for all destinations,
        *pass an empty set of destination IDs to topLevelDestinationIds
        * when building your AppBarConfiguration

        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.blankFragment1,
                R.id.blankFragment2,
                R.id.blankFragment3,
                R.id.blankFragment4,
                R.id.blankFragment5),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        * */

        val appBarConfiguration = AppBarConfiguration(navController.graph,binding.drawerLayout)
        binding.topAppBar.setupWithNavController(navController, appBarConfiguration)
        binding.topAppBar.setOnMenuItemClickListener {
            handleOptionMenu(it)
            true
        }

        //setUp drawer navigation
        binding.navView.setupWithNavController(navController)

    }

    private fun handleOptionMenu(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.blankFragment4,R.id.blankFragment5 -> NavigationUI.onNavDestinationSelected(menuItem,navController)
            else -> false
        }
    }
}