package flavia.figueroa.limapassapp.ui.recovery

import flavia.figueroa.limapassapp.R
import flavia.figueroa.limapassapp.ui.BaseActivity
import flavia.figueroa.limapassapp.ui.BaseViewModel

/*
    Created by: Flavia Figueroa
    Email: ffigueroa052000@gmail.com
    
    9/10/20 - 14:54
    Jesús María, Lima, Perú
*/

class RecoveryPassActivity: BaseActivity() {

    override fun getLayout(): Int = R.layout.activity_forgot_password

    override fun getViewModel(): BaseViewModel? = null

    override fun setUpView() {
    }

    override fun observeViewModel() {
    }
}