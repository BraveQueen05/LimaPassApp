package flavia.figueroa.limapassapp.ui.splash

import android.app.ActivityOptions
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.util.Pair as UtilPair
import android.view.animation.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import flavia.figueroa.limapassapp.R
import flavia.figueroa.limapassapp.databinding.ActivitySplashBinding
import flavia.figueroa.limapassapp.ui.BaseActivity
import flavia.figueroa.limapassapp.ui.BaseViewModel
import flavia.figueroa.limapassapp.ui.login.LoginActivity

/*
    Created by: Flavia Figueroa
    Email: ffigueroa052000@gmail.com

    9/9/20 - 17:11
    Jesús María, Lima, Perú
*/

class SplashActivity : BaseActivity() {

    private var imgSplash: AppCompatImageView ?= null
    private var imgMuni  : AppCompatImageView ?= null

    override fun getLayout(): Int = R.layout.activity_splash

    override fun getViewModel(): BaseViewModel? = null

    override fun setUpView() {
        val binding: ActivitySplashBinding = DataBindingUtil.setContentView(this, getLayout())
        binding.lifecycleOwner = this

        findViewsByIds()
        imgSplashAnimation()
        imgMuniAnimation()
        goToLogin()
    }

    override fun observeViewModel() {
    }

    private fun findViewsByIds(){
        this.imgSplash = findViewById(R.id.imgSplash)
        this.imgMuni   = findViewById(R.id.imgMuni)
    }

    private fun imgSplashAnimation(){
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_scale_anim)
        anim.interpolator = FastOutSlowInInterpolator()
        anim.duration = 600
        this.imgSplash?.startAnimation(anim)
    }

    private fun imgMuniAnimation(){
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_anim)
        anim.startOffset = 300
        anim.duration = 500
        anim.interpolator = AccelerateInterpolator()
        anim.fillAfter = true
        this.imgMuni?.startAnimation(anim)
    }

    private fun goToLogin(){
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            val options = ActivityOptions
                .makeSceneTransitionAnimation(this, UtilPair.create(this.imgSplash as View, "imgSplash"), UtilPair.create(this.imgMuni as View, "imgMuni"))
            startActivity(intent, options.toBundle())
            finish()
        }, 1200)
    }
}