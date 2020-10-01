package flavia.figueroa.limapassapp.ui.host.buses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import flavia.figueroa.limapassapp.R
import flavia.figueroa.limapassapp.databinding.FragmentBusesBinding
import flavia.figueroa.limapassapp.ui.BaseFragment
import flavia.figueroa.limapassapp.ui.BaseViewModel

class BusesFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_buses

    override fun getViewModel(): BaseViewModel? = null

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?, layout: Int): View? {
        val binding: FragmentBusesBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
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