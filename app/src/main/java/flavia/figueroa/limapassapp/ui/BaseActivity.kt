package flavia.figueroa.limapassapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import flavia.figueroa.limapassapp.extensions.getExceptionMessage
import flavia.figueroa.repository.network.exception.GenericException
import flavia.figueroa.repository.network.exception.NetworkException

abstract class BaseActivity : AppCompatActivity() {
    var instanceState: Bundle? = null

    abstract fun getLayout(): Int
    abstract fun getViewModel(): BaseViewModel?
    abstract fun setUpView()
    abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.instanceState = savedInstanceState
        setContentView(getLayout())
        setUpView()
        observeMainViewModel()
    }

    private fun observeMainViewModel() {
        observeViewModel()
        getViewModel()?.let { viewModel ->
            viewModel.loadingLiveData.observe(this, Observer { isLoading ->
                isLoading?.let {
                    if (it) showLoading() else hideLoading()
                }
            })
            viewModel.errorLiveData.observe(this, Observer {
                showError(it)
            })
        }
    }

    fun showError(ex: Exception) {
        when(ex) {
            is NetworkException -> {
                showErrorNetwork()
            }
            is GenericException -> {
                showErrorToast(ex.msg)
            }
            else -> {
                showErrorToast(ex.getExceptionMessage())
            }
        }
    }

    private fun showErrorNetwork() {
        /*this.showDialog(R.layout.dialog_error_connection) {
            this.btnClose.setOnClickListener {
                dismiss()
            }
        }*/
    }

    fun showLoading() {
        println("is loading")
    }

    fun hideLoading() {
        println("hide loading")
    }

    fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}