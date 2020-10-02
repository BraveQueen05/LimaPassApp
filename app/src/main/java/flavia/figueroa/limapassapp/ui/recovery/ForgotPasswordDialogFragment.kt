package flavia.figueroa.limapassapp.ui.recovery

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import flavia.figueroa.limapassapp.R
import flavia.figueroa.limapassapp.databinding.ActivityForgotPasswordBinding

/*
    Created by: Flavia Figueroa
    Email: ffigueroa052000@gmail.com
    
    9/10/20 - 14:54
    Jesús María, Lima, Perú
*/

class ForgotPasswordDialogFragment: DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, R.style.DialogFragmentWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ActivityForgotPasswordBinding = DataBindingUtil.inflate(inflater, R.layout.activity_forgot_password, container, false)
        binding.lifecycleOwner = this

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}