package com.example.quarntine.login

import com.example.quarntine.ui.AccountFragmentViewModel
import com.firebase.ui.auth.data.model.User
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModuels  = module(override = true) {
single {
 AuthViewModel(UserRepository())
}
 single {
  get<Retrofit>().create(UserRepository::class.java)
 }
 single {
  AccountFragmentViewModel(UserRepository())
 }
 single {
    UserRepository()
 }

}