package flavia.figueroa.limapassapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment : Fragment() {
    abstract fun getLayout(): Int
    abstract fun getViewModel(): BaseViewModel?
    abstract fun bindFragment(inflater: LayoutInflater, container: ViewGroup?, layout: Int): View?
    abstract fun setUpArguments()
    abstract fun setUpView()
    abstract fun observeViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return bindFragment(inflater, container, getLayout()) ?: inflater.inflate(getLayout(), container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpArguments()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        observeMainViewModel()
    }

    private fun observeMainViewModel() {
        observeViewModel()
        getViewModel()?.let { viewModel ->
            viewModel.loadingLiveData.observe(this, Observer { isLoading ->
                isLoading?.let {
                    if (it) getMainActivity().showLoading() else getMainActivity().hideLoading()
                }
            })

            viewModel.errorLiveData.observe(this, Observer {
                getMainActivity().showError(it)
            })
        }
    }

    private fun getMainActivity(): BaseActivity = activity as BaseActivity
}