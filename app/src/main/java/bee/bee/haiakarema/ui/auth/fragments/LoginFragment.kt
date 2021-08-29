package bee.bee.haiakarema.ui.auth.fragments

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import bee.bee.haiakarema.R
import bee.bee.haiakarema.base.ActivityFragmentAnnoation
import bee.bee.haiakarema.base.BaseFragment
import bee.bee.haiakarema.databinding.FragmentLoginBinding
import bee.bee.haiakarema.model.Login
import bee.bee.haiakarema.utiles.EmailValidator
import bee.bee.haiakarema.utiles.Preferences.Companion.prefs
import bee.bee.haiakarema.viewmodel.fragments.AuthViewModel

@SuppressLint("NonConstantResourceId")
@ActivityFragmentAnnoation(R.layout.fragment_login)
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val TAG: String get() = this.javaClass.name
    private lateinit var viewModel: AuthViewModel


    override fun initViewModel() {
        viewModel = ViewModelProvider(this@LoginFragment).get(AuthViewModel::class.java)
    }

    override fun setListener() {
        binding?.apply {
            btnSignIn.setOnClickListener {
                hideKeyboard()
                if (checkInputs()) {
                    val loginRequest = Login(
                        etPhoneOrEmail.text.toString(),
                        etPassword.text.toString()
                    )
                    viewModel.login(loginRequest, requireContext())
                        .observe(this@LoginFragment, {
                            goToMainActivity()
                        })
                }

            }
        }
    }

    private fun checkInputs(): Boolean {
        binding!!.apply {
            if (!EmailValidator.getInstance().validate(etPhoneOrEmail.text.toString()) &&
                etPhoneOrEmail.text.toString().isEmpty()
            ) {
                showSnackBar(etPhoneOrEmail, getString(R.string.fillAllRequiredFields))
                return false
            } else if (etPassword.text.toString().isEmpty()) {
                showSnackBar(etPhoneOrEmail, getString(R.string.error_password))
                return false
            }
        }
        return true
    }

}