package flavia.figueroa.limapassapp.ui.signup

import androidx.databinding.DataBindingUtil
import flavia.figueroa.limapassapp.R
import flavia.figueroa.limapassapp.databinding.ActivitySignupBinding
import flavia.figueroa.limapassapp.ui.BaseActivity
import flavia.figueroa.limapassapp.ui.BaseViewModel

/*
    Created by: Flavia Figueroa
    Email: ffigueroa052000@gmail.com
    
    9/10/20 - 14:54
    Jesús María, Lima, Perú
*/

class SignUpActivity: BaseActivity() {

    override fun getLayout(): Int = R.layout.activity_signup

    override fun getViewModel(): BaseViewModel? = null

    override fun setUpView() {
        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, getLayout())
        binding.lifecycleOwner = this
    }

    override fun observeViewModel() {

    }
}