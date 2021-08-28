package bee.bee.haiakarema.ui.auth.fragments

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import bee.bee.haiakarema.R
import bee.bee.haiakarema.base.ActivityFragmentAnnoation
import bee.bee.haiakarema.base.BaseFragment
import bee.bee.haiakarema.databinding.FragmentLoginBinding
import bee.bee.haiakarema.utiles.EmailValidator
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
                    /*val loginRequest = LoginRequest(
                        etPhoneOrEmail.text.toString(),
                        etPassword.text.toString(),
                        prefs.prefsFCMId
                    )*/
                    /*viewModel.getLoginUserData(loginRequest, requireContext(),mainLayout, welcomeLayout.root)
                        .observe(this@LoginFragment, { response ->
                            Log.d(TAG, "onChanged: ${response.token}")
                            val user = response.user
                            prefs.apply {
                                prefsToken = response.token
                                prefsUserId = user.id
                                prefsUserName = user.username
                                prefsFullName = user.name
                                prefsUserEmail = user.email
                                prefsUserPhone = user.phone ?: "null"
                                prefsAge = user.birthdate ?: "null"
                                prefsGender = user.gender ?: "null"
                                prefsUserAvatar = user.avatar
                                prefsBio = user.bio
                            }
                            // findNavController().navigate(R.id.to_next_destination, null, navOptions)
                            goToMainActivity(2500)
                        })*/
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