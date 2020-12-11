package flavia.figueroa.limapassapp.ui.login

import android.content.Intent
import android.view.View
import android.view.animation.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
    private var etEmail         : AppCompatEditText ?= null
    private var etPassword      : AppCompatEditText ?= null

    private lateinit var auth: FirebaseAuth

    override fun getLayout(): Int = R.layout.activity_login

    override fun getViewModel(): BaseViewModel? = null

    override fun setUpView() {
        auth = FirebaseAuth.getInstance()
        auth.currentUser?.let { updateUI() }

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, getLayout())
        binding.lifecycleOwner = this

        window.sharedElementEnterTransition.setDuration(500).interpolator = OvershootInterpolator(1.0f)

        findViewsByIds()
        enterFormAnimation()
        onClickEvents()
    }

    private fun updateUI() {
        startActivity(Intent(this, HostActivity::class.java))
        this.finish()
    }

    private fun findViewsByIds(){
        this.ctrLyForm      = findViewById(R.id.ctrLyForm)
        this.tvSignUp       = findViewById(R.id.tvSignUp)
        this.tvRecoveryPass = findViewById(R.id.tvRecoveryPass)
        this.btnLogin       = findViewById(R.id.btnLogin)
        this.etEmail        = findViewById(R.id.etEmail)
        this.etPassword     = findViewById(R.id.etPassword)
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
            if(validation()) {
                this.auth.signInWithEmailAndPassword(this.etEmail?.text.toString(), this.etPassword?.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, HostActivity::class.java))
                        this.finish()
                    } else {
                        Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
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

    private fun validation(): Boolean {
        if (this.etEmail?.text.toString().isEmpty() || this.etPassword?.text.toString().isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}