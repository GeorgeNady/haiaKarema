package bee.bee.haiakarema.ui.main

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import bee.bee.haiakarema.R
import bee.bee.haiakarema.base.BaseActivity
import bee.bee.haiakarema.databinding.ActivityMainBinding
import bee.bee.haiakarema.ui.auth.AuthActivity
import bee.bee.haiakarema.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate
) {

    companion object {
        lateinit var mainActivity: MainActivity
    }

    override val TAG: String get() = this.javaClass.name

    override fun initialization() {
        mainActivity = this
        if (token.isEmpty()) {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun setListener() {

        binding.apply {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
            val navController = navHostFragment.navController
            bottomNavigation.setupWithNavController(navController)
        }

    }

    private fun navigateHandler(res: Int, bundle: Bundle? = null) {
        binding.mainNavHostFragment.findNavController()
            .navigate(res, bundle)
    }

    private fun getActionBarH(): Float {
        val tv = TypedValue()
        var actionBarHeight = 0
        if (theme.resolveAttribute(attr.actionBarSize, tv, true)) {
            actionBarHeight =
                TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
        }
        return actionBarHeight.toFloat()
    }

}