package flavia.figueroa.limapassapp.ui.host.mycard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import flavia.figueroa.limapassapp.R
import flavia.figueroa.limapassapp.databinding.FragmentMyCardBinding
import flavia.figueroa.limapassapp.ui.BaseFragment
import flavia.figueroa.limapassapp.ui.BaseViewModel
import flavia.figueroa.limapassapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_my_card.*

class MyCardFragment : BaseFragment(), IRecharge, IPayment {
    private lateinit var auth: FirebaseAuth

    override fun getLayout(): Int = R.layout.fragment_my_card

    override fun getViewModel(): BaseViewModel? = null

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?, layout: Int): View? {
        val binding: FragmentMyCardBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun setUpArguments() {
    }

    override fun setUpView() {
        auth = FirebaseAuth.getInstance()
        this.btnRecharge.setOnClickListener {
            val df = RechargeDialogFragment(this)
            df.show(childFragmentManager, "Recharge")
        }
        this.btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(context, LoginActivity::class.java))
            activity?.finish()
        }
    }

    override fun observeViewModel() {
    }

    override fun passToPayment(amount: Double) {
        val df = PaymentDialogFragment(this)
        val bundle = bundleOf("AMOUNT" to amount)
        df.arguments = bundle
        df.show(childFragmentManager, PaymentDialogFragment::class.toString())
    }

    override fun success(amount: Double) {
        this.amount.text = "S/ $amount"
    }
}