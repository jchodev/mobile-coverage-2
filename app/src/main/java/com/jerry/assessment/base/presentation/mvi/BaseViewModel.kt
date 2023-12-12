package com.jerry.assessment.base.presentation.mvi


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<INTENT: MviIntent, ACTION: MviAction>() : ViewModel() {

    //from UI Intent Channel
    private val intentChannel = Channel<INTENT>()

    fun sendIntent(intent: INTENT) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

    //base on intent type to fire action
    protected abstract suspend fun handleIntent(intent: INTENT) : List<ACTION>
    protected abstract suspend fun handleAction(actions: ACTION)

    //which is send event to GA4 / tracking logging
    protected abstract suspend fun handleIntentActionsTracker(intent: INTENT, actions: List<ACTION>)

    fun initIntent(){
        viewModelScope.launch {
            intentChannel.consumeAsFlow()
                .onStart {
                    Timber.d("BaseViewModel::initIntent::onStart")
                }
                .collect {
                    Timber.d("BaseViewModel::intent::${it}")
                    val actions = handleIntent(it)
                    Timber.d("BaseViewModel::action::${actions}")
                    handleIntentActionsTracker(it, actions)
                    actions.forEach {
                        handleAction(it)
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        intentChannel.cancel()
    }
}