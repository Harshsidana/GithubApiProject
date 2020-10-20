package com.example.githubapiproject.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.githubapiproject.R
import com.example.githubapiproject.adapters.GithubAdapter
import com.example.githubapiproject.entities.ResourceState
import com.example.githubapiproject.extensions.observe
import com.example.githubapiproject.viewModel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    val adapter = GithubAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvItems.adapter = adapter
        rvItems.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )
        mainViewModel.getUserData()
        observe(mainViewModel.resultLiveData)
        {
            when(it)
            {
                is ResourceState.Success->{
                    adapter.addList(it.body)

                }
                is ResourceState.Failure->{

                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()

                }

            }
        }
    }


}