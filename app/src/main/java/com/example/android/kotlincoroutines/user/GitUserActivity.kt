package com.example.android.kotlincoroutines.user

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.kotlincoroutines.R
import com.example.android.kotlincoroutines.user.RetrofitFactory.makeRetrofitService

/**
 * Created by Chandra Kant on 17/10/19.
 */
class GitUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_user)
        val database = getDatabase(this)
        val repository = UserRepository(makeRetrofitService(), database.userDao)
        val viewModel = ViewModelProviders
                .of(this, UserListViewModel.FACTORY(repository))
                .get(UserListViewModel::class.java)

        viewModel.onMainViewClicked()
        viewModel.title.observe(this, Observer { value ->
            value?.let {
                Log.d("USERLIST", it.toString())
            }
        })


    }
}