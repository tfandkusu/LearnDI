package com.tfandkusu.learndi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    /**
     * DIを使った場合
     */
    private val presenter: MainPresenter by viewModel()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        presenter.load(1)
    }
}
