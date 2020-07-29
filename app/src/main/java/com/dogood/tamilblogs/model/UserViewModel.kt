package com.dogood.tamilblogs.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dogood.tamilblogs.repository.AppApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel :ViewModel(){

    var session_id:String?=null

    val api_key="55920c043c993d39026c3293f309ca20"

    private val _userDetailResponse=MutableLiveData<String>()

    val userDetailResponse:LiveData<String>
    get() = _userDetailResponse

    private val _username=MutableLiveData<String>()

    val username:LiveData<String>
        get() = _username

    private var viewmodeljob= Job()

    private var coroutineScope = CoroutineScope(viewmodeljob+Dispatchers.Main)

    fun getDetails(){
        if(!session_id.isNullOrEmpty()){

            coroutineScope.launch {
                var getDetails=AppApiService.AppApi.retrofitService.getDetails(api_key,session_id!!)
                try{
                    var details=getDetails.await()
                    _username.value=details.username
                }catch (e:Exception){
                    _username.value=e.toString()
                }
            }
        }
    }

}