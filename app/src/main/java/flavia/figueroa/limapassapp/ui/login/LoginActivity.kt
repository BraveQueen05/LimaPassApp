package flavia.figueroa.limapassapp.ui.login

import android.content.Intent
import android.view.View
import android.view.animation.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import flavia.figueroa.limapassapp.R
import flavia.figueroa.limapassapp.databinding.ActivityLoginBinding
import flavia.figueroa.limapassapp.extensions.changeLenguageTo
import flavia.figueroa.limapassapp.ui.BaseActivity
import flavia.figueroa.limapassapp.ui.BaseViewModel
import flavia.figueroa.limapassapp.ui.host.HostActivity
import flavia.figueroa.limapassapp.ui.recovery.ForgotPasswordDialogFragment
import flavia.figueroa.limapassapp.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*

/*
    Created by: Flavia Figueroa
    Email: ffigueroa052000@gmail.com
    
    9/9/20 - 17:28
    Jesús María, Lima, Perú
*/

class LoginActivity: BaseActivity() {

    private var ctrLyForm       : ConstraintLayout  ?= null
    private var tvSignUp        : AppCompatTextView ?= null
    private var tvRecoveryPass  : AppCompatTextView ?= null
    private var btnLogin        : AppCompatButton   ?= null

    override fun getLayout(): Int = R.layout.activity_login

    override fun getViewModel(): BaseViewModel? = null

    override fun setUpView() {
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, getLayout())
        binding.lifecycleOwner = this

        window.sharedElementEnterTransition.setDuration(500).interpolator = OvershootInterpolator(1.0f)

        findViewsByIds()
        enterFormAnimation()
        onClickEvents()
    }

    private fun findViewsByIds(){
        this.ctrLyForm      = findViewById(R.id.ctrLyForm)
        this.tvSignUp       = findViewById(R.id.tvSignUp)
        this.tvRecoveryPass = findViewById(R.id.tvRecoveryPass)
        this.btnLogin       = findViewById(R.id.btnLogin)
    }

    private fun enterFormAnimation(){
        val anim = AnimationUtils.loadAnimation(this,  R.anim.fade_translate_anim)
        anim.duration = 500
        anim.interpolator = OvershootInterpolator(1.0f)
        this.ctrLyForm?.startAnimation(anim)
    }

    override fun observeViewModel() {
    }

    private fun onClickEvents(){
        this.btnLogin?.setOnClickListener {
            startActivity(Intent(this, HostActivity::class.java))
            this.finish()
        }

        this.tvSignUp?.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        this.tvRecoveryPass?.setOnClickListener {
            ForgotPasswordDialogFragment().show(supportFragmentManager, ForgotPasswordDialogFragment().toString())
        }
        this.btnEn.setOnClickListener {
            changeLenguageTo("en")
        }
        this.btnEs.setOnClickListener {
            changeLenguageTo()
        }
    }
}