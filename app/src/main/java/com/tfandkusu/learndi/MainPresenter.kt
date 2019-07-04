package com.tfandkusu.learndi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(private val repository: CardRepository) : ViewModel() {
    val card = MutableLiveData<Card>()

    val progress = MutableLiveData<Boolean>()

    fun load(refresh: Boolean, id: Int) = GlobalScope.launch {
        progress.postValue(true)
        val card = repository.getCard(refresh, id)
        progress.postValue(false)
        this@MainPresenter.card.postValue(card)
    }

}