package bee.bee.haiakarema.ui.auth

import bee.bee.haiakarema.base.BaseActivity
import bee.bee.haiakarema.databinding.ActivityAuthBinding
import bee.bee.hoshaapp.viewmodel.AuthActivityViewModel


class AuthActivity : BaseActivity<ActivityAuthBinding, AuthActivityViewModel>(
    ActivityAuthBinding::inflate
) {

    override val TAG: String get() = this.javaClass.name
    companion object {
        lateinit var authActivity: AuthActivity
    }

    override fun initialization() {
        authActivity = this
    }

    override fun setListener() {

    }
}