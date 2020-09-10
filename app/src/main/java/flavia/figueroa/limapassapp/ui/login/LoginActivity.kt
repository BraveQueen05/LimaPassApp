package flavia.figueroa.limapassapp.ui.login

import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import flavia.figueroa.limapassapp.R
import flavia.figueroa.limapassapp.databinding.ActivityLoginBinding
import flavia.figueroa.limapassapp.ui.BaseActivity
import flavia.figueroa.limapassapp.ui.BaseViewModel

/*
    Created by: Flavia Figueroa
    Email: ffigueroa052000@gmail.com
    
    9/9/20 - 17:28
    Jesús María, Lima, Perú
*/

class LoginActivity: BaseActivity() {

    var ctrLyForm: ConstraintLayout ?= null

    override fun getLayout(): Int = R.layout.activity_login

    override fun getViewModel(): BaseViewModel? = null

    override fun setUpView() {
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, getLayout())
        binding.lifecycleOwner = this

        findViewsByIds()
        enterFormAnimation()
    }

    private fun findViewsByIds(){
        this.ctrLyForm = findViewById(R.id.ctrLyForm)
    }

    private fun enterFormAnimation(){
        val anim = AnimationUtils.loadAnimation(this,  R.anim.fade_anim)
        anim.duration = 850
        this.ctrLyForm?.startAnimation(anim)
    }

    override fun observeViewModel() {
    }
}