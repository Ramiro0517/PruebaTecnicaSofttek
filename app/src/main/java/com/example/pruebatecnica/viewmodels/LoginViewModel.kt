package com.example.pruebatecnica.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel:ViewModel() {


    private val _authenticationResult = MutableLiveData<Boolean>()
    val authenticationResult: LiveData<Boolean> = _authenticationResult

    fun authenticate(username: String, password: String) {
        _authenticationResult.value = username == "admin" && password == "Password*123"
    }
}