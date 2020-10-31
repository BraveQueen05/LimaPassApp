package flavia.figueroa.limapassapp.ui.host.mycard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import flavia.figueroa.limapassapp.R
import kotlinx.android.synthetic.main.recharge_dialog_fragment.*

/*
    Created by: Flavia Figueroa
    Email: ffigueroa052000@gmail.com
    
    10/30/20 - 22:36
    Solera Mobile
*/

class RechargeDialogFragment(private val listener: IRecharge): DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, R.style.DialogFragmentWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recharge_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.btnRechargeDone.setOnClickListener {
            if (!this.edtAmount.text.toString().isEmpty()) {
                this.listener.recharge(edtAmount.text.toString().toInt())
                dismiss()
            }else{
                this.dismiss()
            }
        }
    }
}

interface IRecharge{
    fun recharge(amount: Int)
}