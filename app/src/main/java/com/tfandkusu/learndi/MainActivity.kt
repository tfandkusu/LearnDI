package com.tfandkusu.learndi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    // DIを使った場合
    private val presenter: MainPresenter by viewModel()

    // IDを使わない場合
    //private lateinit var presenter: MainPresenter


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // DIを使わない場合
//        presenter = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                @Suppress("UNCHECKED_CAST")
//                return MainPresenter(CardRepositoryFactory.getInstance()) as T
//            }
//        }).get(MainPresenter::class.java)
        setContentView(R.layout.activity_main)

        presenter.card.observe(this, Observer { card ->
            card?.let {
                company.text = it.company
                divisionAndTitle.text = "${it.division} ${it.title}"
                name.text = it.name
            }
        })
        presenter.progress.observe(this, Observer { flag ->
            flag?.let {
                if (it)
                    progress.visibility = View.VISIBLE
                else
                    progress.visibility = View.GONE
            }
        })
        presenter.load(false, 1)
    }
}
