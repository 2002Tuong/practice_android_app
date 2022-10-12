package com.example.testcomponent

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBar

import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.testcomponent.databinding.ActivityMainBinding
import javax.security.auth.callback.Callback


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

        //handling floating context menu
        val tvFloatingContextMenu = binding.floatingContextMenuTv
        registerForContextMenu(tvFloatingContextMenu)

        //handling contextual action bar
        binding.showContextualActionBar.setOnLongClickListener {
            startActionMode(object : ActionMode.Callback {
                /*  Action mode:
                UI mode that lets you replace parts of normal UI interactions temporarily
                For example: Selecting a section of text or long-pressing an item could trigger action mode
                */

                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    menuInflater.inflate(R.menu.contextual_action_bar,menu)
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return false
                }

                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    when(item?.itemId) {
                        R.id.Edit -> {Toast.makeText(this@MainActivity, "edit text success ",Toast.LENGTH_SHORT).show()
                        return true }
                        R.id.Share ->{Toast.makeText(this@MainActivity, "share text success ",Toast.LENGTH_SHORT).show()
                        return true }
                        else -> return false
                    }

                }

                override fun onDestroyActionMode(mode: ActionMode?) {

                }
            })
            true
        }
    }

    private fun handleOptionMenu(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.blankFragment4,R.id.blankFragment5 -> NavigationUI.onNavDestinationSelected(menuItem,navController)
            else -> false
        }
    }

    /*
    handling floating context menu

    Floating context menu — [long-press on a View]
    **User can modify View or use it in some fashion
    **User performs action on one View at a time
     */
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.floating_context_menu,menu)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.context_share -> Toast.makeText(this@MainActivity,"do you want to share this?",Toast.LENGTH_LONG).show()
            R.id.context_edit -> Toast.makeText(this@MainActivity,"do you want to edit this?",Toast.LENGTH_LONG).show()
            else ->Toast.makeText(this@MainActivity,"nothing to do",Toast.LENGTH_LONG).show()
        }

        return super.onContextItemSelected(item)
    }




}

//class HandleActionBar : ActionMode.Callback {
//    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun onDestroyActionMode(mode: ActionMode?) {
//        TODO("Not yet implemented")
//    }
//}