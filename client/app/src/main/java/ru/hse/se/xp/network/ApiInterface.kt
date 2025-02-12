package ru.hse.se.xp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import ru.hse.se.xp.model.ListItem
import ru.hse.se.xp.model.UserList

interface ApiInterface {

    companion object {
        private const val HEADER_ACCEPT = "accept:application/json"
    }

    @Headers(HEADER_ACCEPT)
    @GET("/api/lists/user/lists")
    fun getUserLists(
        @Query("user_id") userId: Int
    ): Call<List<UserList>>

    @Headers(HEADER_ACCEPT)
    @GET("lists/list_id")
    fun getList(
        @Path("list_id") listId: Int
    ): Call<List<ListItem>>
}
