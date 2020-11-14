package flavia.figueroa.limapassapp.ui.host.mycard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import flavia.figueroa.limapassapp.R
import kotlinx.android.synthetic.main.payment_dialog_fragment.*

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    11/14/20 - 11:15
    Solera Mobile
*/

class PaymentDialogFragment(private val listener: IPayment): DialogFragment() {

    var amount: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, R.style.DialogFragmentWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            this.amount = it.getDouble("AMOUNT")
        }

        return inflater.inflate(R.layout.payment_dialog_fragment, container, false)
    }

    //Simular ingreso de datos de la tarjeta
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvAmount.text = "S/ $amount"

        btnDone.setOnClickListener {
            Toast.makeText(context, "Transacción correcta", Toast.LENGTH_SHORT).show()
            this.listener.success(this.amount)
            dismiss()
        }

        btnClose.setOnClickListener{
            dismiss()
        }
    }
}

interface IPayment{
    fun success(amount:Double)
}

