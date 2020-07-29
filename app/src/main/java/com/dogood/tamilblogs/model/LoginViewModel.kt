package com.dogood.tamilblogs.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dogood.tamilblogs.databinding.FragmentLoginBinding
import com.dogood.tamilblogs.repository.AppApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel:ViewModel() {
    var username:String?=null
    var password:String?=null

    var session_id_value:String?=null

    private val _response_value=MutableLiveData<String>()
    val api_key="55920c043c993d39026c3293f309ca20"

    val response_value:LiveData<String>
    get() = _response_value

    private val _isLoggedIn=MutableLiveData<Boolean>()

    val isLoggedIn:LiveData<Boolean>
    get() = _isLoggedIn

    private val _isClickedLogin=MutableLiveData<Boolean>()

    val isClickedLogin:LiveData<Boolean>
        get() = _isClickedLogin


    private var viewmodeljob = Job()
    private var coroutineScope= CoroutineScope(viewmodeljob+Dispatchers.Main)



    fun getResponse(){
        if(username.isNullOrEmpty() || password.isNullOrEmpty()){
            _isClickedLogin.value=false
        }else {
            _isClickedLogin.value=true
            coroutineScope.launch {

                var getTokenDeferred = AppApiService.AppApi.retrofitService.getToken(api_key)
                try {
                    var token = getTokenDeferred.await()
                    getSession(token.request_token)
                } catch (e: Exception) {

                }
            }
        }
    }

    fun getSession(token:String){
        coroutineScope.launch {
            var getSessionResponse=AppApiService.AppApi.retrofitService.createSessionWithLogin("1dhana625","Dhana@123",token,api_key)
            try {
                var session_token=getSessionResponse.await()
                getSessionId(session_token.request_token)
            }catch (e:Exception){

            }
        }
    }

    fun getSessionId(token:String){
        coroutineScope.launch {
            var getSessionIdResponse =
                AppApiService.AppApi.retrofitService.createSessionId(token, api_key)
            try {
                var session_id = getSessionIdResponse.await()
                _response_value.value=session_id.session_id
                session_id_value=session_id.session_id
                isLoggedIn()
            } catch (e: Exception) {
                _response_value.value=e.message
            }
        }
    }

    fun isLoggedIn(){
        _isClickedLogin.value=false
        _isLoggedIn.value=true
    }

    override fun onCleared() {
        super.onCleared()
        viewmodeljob.cancel()
    }
}