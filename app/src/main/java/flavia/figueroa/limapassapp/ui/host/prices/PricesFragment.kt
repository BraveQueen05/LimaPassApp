package flavia.figueroa.limapassapp.ui.host.prices

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import flavia.figueroa.limapassapp.R
import flavia.figueroa.limapassapp.databinding.FragmentPricesBinding
import flavia.figueroa.limapassapp.ui.BaseFragment
import flavia.figueroa.limapassapp.ui.BaseViewModel

class PricesFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_prices

    override fun getViewModel(): BaseViewModel? = null

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?, layout: Int): View? {
        val binding: FragmentPricesBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun setUpArguments() {
    }

    override fun setUpView() {
    }

    override fun observeViewModel() {
    }
}