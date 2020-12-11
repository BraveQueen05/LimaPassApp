package flavia.figueroa.limapassapp.ui.signup

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import flavia.figueroa.limapassapp.R
import flavia.figueroa.limapassapp.databinding.ActivitySignupBinding
import flavia.figueroa.limapassapp.ui.BaseActivity
import flavia.figueroa.limapassapp.ui.BaseViewModel
import flavia.figueroa.limapassapp.ui.login.LoginActivity

/*
    Created by: Flavia Figueroa
    Email: ffigueroa052000@gmail.com
    
    9/10/20 - 14:54
    Jesús María, Lima, Perú
*/

class SignUpActivity : BaseActivity() {

    private var etDNI: AppCompatEditText? = null
    private var etEmail: AppCompatEditText? = null
    private var etPassword: AppCompatEditText? = null
    private var etConfirmPassword: AppCompatEditText? = null
    private var btnSignup: AppCompatButton? = null

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun getLayout(): Int = R.layout.activity_signup

    override fun getViewModel(): BaseViewModel? = null

    override fun setUpView() {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, getLayout())
        binding.lifecycleOwner = this

        findViewsByIds()
        onClickEvents()
    }

    private fun findViewsByIds() {
        this.etDNI = findViewById(R.id.etDNI)
        this.etEmail = findViewById(R.id.etEmail)
        this.etPassword = findViewById(R.id.etPassword)
        this.etConfirmPassword = findViewById(R.id.etConfirmPassword)
        this.btnSignup = findViewById(R.id.btnSignup)
    }

    override fun observeViewModel() {

    }

    private fun onClickEvents() {
        this.btnSignup?.setOnClickListener {
            if (validation()) {
                this.auth.createUserWithEmailAndPassword(this.etEmail?.text.toString(), this.etPassword?.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val map = hashMapOf(
                            "dni" to this.etDNI?.text.toString(),
                            "email" to this.etEmail?.text.toString(),
                            "password" to this.etPassword?.text.toString()
                        )
                        database.child("User").setValue(map).addOnCompleteListener {
                            Toast.makeText(this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            this.finish()
                        }
                    } else {
                        Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun validation(): Boolean {
        if (this.etDNI?.text.toString().isEmpty()||this.etPassword?.text.toString().isEmpty() || this.etConfirmPassword?.text.toString().isEmpty()){
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }
        if (this.etDNI?.text.toString().length < 8) {
            Toast.makeText(this, "No es un DNI válido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (this.etPassword?.text.toString() != this.etConfirmPassword?.text.toString()) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}