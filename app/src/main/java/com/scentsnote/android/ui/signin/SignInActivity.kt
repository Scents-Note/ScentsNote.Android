package com.scentsnote.android.ui.signin

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.scentsnote.android.R
import com.scentsnote.android.ScentsNoteApplication.Companion.firebaseAnalytics
import com.scentsnote.android.databinding.ActivitySignInBinding
import com.scentsnote.android.ui.MainActivity
import com.scentsnote.android.ui.signup.SignUpEmailActivity
import com.scentsnote.android.viewmodel.signin.SignInViewModel
import com.scentsnote.android.utils.base.BaseActivity
import com.scentsnote.android.utils.extension.setPageViewEvent
import com.scentsnote.android.utils.extension.startActivity
import com.scentsnote.android.utils.extension.startActivityWithFinish

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            viewModel = signInViewModel
        }

        binding.edtSignInEmail.requestFocus()

        signInViewModel.checkRegisterInfo()
        checkNextBtn()
    }

    override fun onResume() {
        super.onResume()

        firebaseAnalytics.setPageViewEvent("Login", this::class.java.name)
    }

    private fun checkNextBtn() {
        signInViewModel.isValidEmail.observe(this, Observer {
            signInViewModel.checkLoginNextBtn()
        })
        signInViewModel.isValidPassword.observe(this, Observer {
            signInViewModel.checkLoginNextBtn()
        })
    }

    fun onClickSignInBtn(view: View) {
        signInViewModel.postLoginInfo()

        signInViewModel.isValidLogin.observe(this, Observer { isValidLogin ->
            isValidLogin?.let {
                if (isValidLogin) {
                    this.startActivityWithFinish(MainActivity::class.java)
                }
            }
        })
    }

    fun onClickSignUpBtn(view: View) {
        this.startActivity(SignUpEmailActivity::class.java)
    }

    fun onClickBackBtn(view: View) {
        finish()
    }
}