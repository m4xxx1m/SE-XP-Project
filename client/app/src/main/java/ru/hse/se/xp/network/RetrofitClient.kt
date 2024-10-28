package ru.hse.se.xp.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.hse.se.xp.model.ListItem
import ru.hse.se.xp.model.UserList

object RetrofitClient {

    private const val BASE_URL: String = "http://localhost:8080/"

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    private val retrofitCall: ApiInterface = Retrofit.Builder()
        .client(getHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    private fun <T> invokeCall(
        call: Call<T>,
        onSuccess: ((T?) -> Unit)? = null,
        onFailure: (() -> Unit)? = null
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess?.invoke(response.body())
                } else {
                    onFailure?.invoke()
                }
            }

            override fun onFailure(call: Call<T>, error: Throwable) {
                onFailure?.invoke()
            }
        })
    }

    fun getUserLists(
        userId: Int,
        onSuccess: ((List<UserList>?) -> Unit)? = null,
        onFailure: (() -> Unit)? = null
    ) {
        invokeCall(
            retrofitCall.getUserLists(userId),
            onSuccess,
            onFailure
        )
    }

    fun getList(
        listId: Int,
        onSuccess: ((List<ListItem>?) -> Unit)? = null,
        onFailure: (() -> Unit)? = null
    ) {
        invokeCall(
            retrofitCall.getList(list_id),
            onSuccess,
            onFailure
        )
    }
}