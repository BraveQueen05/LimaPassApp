package flavia.figueroa.limapassapp.ui.host

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import flavia.figueroa.limapassapp.R
import flavia.figueroa.limapassapp.ui.BaseActivity
import flavia.figueroa.limapassapp.ui.BaseViewModel

/*
    Created by: Flavia Figueroa
    Email: ffigueroa052000@gmail.com
    
    9/30/20 - 22:41
    Jesús María, Lima, Perú
*/

class HostActivity: BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_host

    override fun getViewModel(): BaseViewModel? = null

    override fun setUpView() {
        initNavigationComponent()
    }

    private fun initNavigationComponent(){
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    override fun observeViewModel() {

    }
}