package khamroev.handybook.networking


import khamroev.handybook.model.SignUp
import khamroev.handybook.model.Signin
import khamroev.handybook.model.UserToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface APIService {
//    @GET("/book-api")
//    fun getBooks():Call<List<Book>>

//    @GET("/book-api/view")
//    fun getBook(@Query("id") id:Int):Call<Book>

//    @GET("/book-api/main-book")
//    fun getMainBook():Call<Book>
//    @GET("/book-api/category")
//    fun getBookByCategory(@Query("name") name:String):Call<List<Book>>

//    @GET("/book-api/comment")
//    fun getComments(@Query("id") id: Int):Call<List<Comment>>

//    @GET("/book-api/search-name")
//    fun search(@Query("name") name: String):Call<List<Book>>

    @POST("/book-api/register")
    fun signup(@Body signUp: SignUp): Call<UserToken>

    @POST("/book-api/login")
    fun login(@Body signin: Signin): Call<UserToken>
}