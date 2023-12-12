package com.jerry.assessment.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jerry.assessment.R
import com.jerry.assessment.designllib.dialog.HmAlertDialog
import com.jerry.assessment.designllib.loading.HmLoading
import com.jerry.assessment.designllib.theme.AssessmentTheme
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability

import com.jerry.assessment.features.mobilecoverage.presentation.components.MobileCoverageScreen
import com.jerry.assessment.features.mobilecoverage.presentation.mvi.MobileCoverageIntent
import com.jerry.assessment.features.mobilecoverage.presentation.viewmodel.MobileCoverageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MobileCoverageViewModel>()


    //onClick event ...
    private val onSearchClick: () -> Unit = {
        viewModel.sendIntent(
            intent = MobileCoverageIntent.SearchButtonClick
        )
    }

    private val onPostCodeValueChange: (String) -> Unit = {
        viewModel.sendIntent(
            intent = MobileCoverageIntent.PostCodeValueChange(
                postCode = it
            )
        )
    }

    private val onRetryClick: () -> Unit = {
        viewModel.sendIntent(
            intent = MobileCoverageIntent.RetryClick
        )
    }

    private val onDismissClick: () -> Unit = {
        viewModel.sendIntent(
            intent = MobileCoverageIntent.DismissDialog
        )
    }

    private val onAddressSelected: (MobileAvailability) -> Unit = {
        viewModel.sendIntent(
            intent = MobileCoverageIntent.NewAddressChange(
                newMobileAvailability = it
            )
        )
    }

    private val loadCompose = @Composable {
        HmLoading()
    }

    private val retryDialogCompose: @Composable (Any) -> Unit =
        @Composable {
            RetryDialog(
                message = it,
                onRetryClick = { onRetryClick() },
                onLeftClick = { onDismissClick() }
            )
        }

    /* testing LeakCanery */
    /*
    object Class1 {
        lateinit var context: Context
    }
    fun onCreate(){
     ...
     Class1.context = this
     ...
    }
    */

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("activity::onCreate")
        setContent {
            AssessmentTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                            title = {
                                Text("Mobile Coverage", color = MaterialTheme.colorScheme.onPrimary)
                            }
                        )
                    }
                ) {
                    MobileCoverageScreen(
                        modifier = Modifier.padding(it),
                        onSearchBtnClick = onSearchClick,
                        onPostCodeValueChange = onPostCodeValueChange,
                        loadingState = viewModel.loadingState.collectAsState(),
                        errorState = viewModel.errorState.collectAsState(),
                        retryDialog = {
                            retryDialogCompose(it)
                        },
                        loadingCompose = loadCompose,
                        mobileAvailabilitiesState = viewModel.mobileAvailabilitiesState.collectAsState(),
                        selectedMobileAvailabilityState = viewModel.selectedMobileAvailabilityState.collectAsState(),
                        onAddressSelected = onAddressSelected
                    )
                }
            }
        }

        observeViewModelEvents()

        //init mvi
        viewModel.initIntent()
    }

    private fun observeViewModelEvents(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dismissKeyboardState.collectLatest {
                    hideKeyboard()
                }
            }
        }

    }

    private fun hideKeyboard(){
        this.currentFocus?.let { view ->
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    @Composable
    private fun RetryDialog(message: Any, onRetryClick :() -> Unit, onLeftClick: ()-> Unit ){
        val message = getErrorMessage(message)
        HmAlertDialog(
            title = "",
            message = message,
            onLeftClick = onLeftClick,
            rightBtnStr = stringResource(id = R.string.retry),
            onRightClick = onRetryClick
        )
    }

    @Composable
    private fun getErrorMessage(mess: Any) : String {
        var message = ""
        message = if (mess is String)
            mess
        else {
            when (mess) {
                is SocketTimeoutException -> {
                    stringResource(R.string.time_out_error)
                }

                is UnknownHostException -> {
                    stringResource(R.string.unknown_host_error)
                }

                else -> {
                    stringResource(R.string.some_error)
                }
            }
        }
        return message
    }

    override fun onStart() {
        super.onStart()
        Timber.d("activity::onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("activity::onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("activity::onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("activity::onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("activity::onDestroy")
    }
}