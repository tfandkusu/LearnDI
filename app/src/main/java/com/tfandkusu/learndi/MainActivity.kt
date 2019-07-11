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
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    // DIを使った場合
    private val store: MainStore by viewModel()


    private val actionCreator: MainActionCreator by inject()

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

        // 下スワイプリロード
        swipeRefresh.setOnRefreshListener {
            actionCreator.load(true, 1)
        }

        store.card.observe(this, Observer { card ->
            card?.let {
                company.text = it.company
                divisionAndTitle.text = "${it.division} ${it.title}"
                name.text = it.name
            }
        })
        // プログレスの表示
        store.progress.observe(this, Observer { flag ->
            flag?.let {
                if (it)
                    progress.visibility = View.VISIBLE
                else
                    progress.visibility = View.GONE
            }
        })
        // リフレッシュ中の表示
        store.refresh.observe(this, Observer { flag ->
            flag?.let {
                swipeRefresh.isRefreshing = it
            }
        })
        // リフレッシュ操作ができるか
        store.refreshEnabled.observe(this, Observer { flag ->
            flag?.let {
                swipeRefresh.isEnabled = it
            }
        })
        actionCreator.load(false, 1)
    }
}
