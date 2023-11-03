    package khamroev.handybook.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import khamroev.handybook.adapter.BookClicked
import khamroev.handybook.adapter.CategoryAdapter
import khamroev.handybook.adapter.SearchAdapter
import khamroev.handybook.databinding.FragmentSearchBinding
import khamroev.handybook.model.Book
import khamroev.handybook.model.Category
import khamroev.handybook.networking.APIClient
import khamroev.handybook.networking.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private lateinit var binding : FragmentSearchBinding
    private val api = APIClient.getInstance().create(APIService::class.java)
    private lateinit var adapter : SearchAdapter
    private lateinit var allBooks : List<Book>

    private fun goToBookScreen() {
        //TODO go to book screen
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        binding.searchRecycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = SearchAdapter(listOf(), object : BookClicked {


            override fun onClicked(book: Book) {
                TODO("Not yet implemented")
            }
        })
        binding.searchRecycler.adapter = adapter


        api.getBooks().enqueue(object : retrofit2.Callback<List<Book>>{
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                if (!response.isSuccessful) return
                val books = response.body()!!
                Log.d("TAG", "${response.body()}")
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Log.d("TAG", "$t")
            }

        })

        setAllRecycler()
        setSearchView()

//        setCategoryAdapter()
























        return binding.root
    }


//    fun setSearch() {
//        binding.searchSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                binding.searchSearchView.clearFocus()
//                return false
//            }
//
//            @SuppressLint("NotifyDataSetChanged")
//            override fun onQueryTextChange(newText: String?): Boolean {
//                if (newText != null) {
//
//                    api.search(newText).enqueue(object : Callback<List<Book>>{
//                        override fun onResponse(
//                            call: Call<List<Book>>,
//                            response: Response<List<Book>>
//                        ) {
//                            if (!response.isSuccessful) return
//                            setAllBooks(response.body()!!)
//                        }
//
//                        override fun onFailure(call: Call<List<Book>>, t: Throwable) {
//                            Log.d("TAG", "$t")
//                        }
//                    })
//                    return true
//                }
//                setAllBooks(allBooks)
//                return false
//            }
//
//        })
//    }
//
//
//
//    fun setCategoryAdapter() {
//        binding.searchRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        api.getCategories().enqueue(object : Callback<List<Category>>{
//            override fun onResponse(
//                call: Call<List<Category>>,
//                response: Response<List<Category>>
//            ) {
//                if (!response.isSuccessful) return
//                binding.searchRecycler.adapter = CategoryAdapter(response.body()!!, requireContext(), binding.searchRecycler, object : CategoryAdapter.CategoryPressed{
//                    override fun onPressed(category: String?) {
//                        if (category == null){
//                            setAllBooks(allBooks)
//                            binding.searchRecycler.visibility = View.VISIBLE
//                            return
//                        }
//                        binding.searchRecycler.visibility = View.GONE
//                        setCategoryChanger(category)
//                    }
//                })
//            }
//
//            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
//                Log.d("TAG", "$t")
//            }
//        })
//    }
//
//    fun setCategoryChanger(category: String) {
//        api.getBookByCategory(category).enqueue(object : Callback<List<Book>>{
//            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
//                if (!response.isSuccessful) return
//                val books = response.body()!!
//                setAllBooks(books)
//            }
//
//            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
//                Log.d("TAG", "$t")
//            }
//        })
//    }


    private fun setSearchView() {
        binding.searchSearchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchSearchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    api.search(newText).enqueue(object : retrofit2.Callback<List<Book>>{
                        override fun onResponse(
                            call: Call<List<Book>>,
                            response: Response<List<Book>>
                        ) {
                            if (!response.isSuccessful) return
                            val books = response.body()!!
                            setBooks(books)
                        }

                        override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                            Log.d("TAG", "$t")
                        }

                    })
                }
                setBooks(allBooks)
                return false
            }

        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAllBooks(books: List<Book>) {
        adapter.books = books
        adapter.notifyDataSetChanged()
    }

    private fun setAllRecycler() {
        api.getBooks().enqueue(object : retrofit2.Callback<List<Book>>{
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                if (!response.isSuccessful) return
                val books = response.body()!!
                setAllBooksList(books)
                setBooks(books)
                Log.d("TAG", "${response.body()}")
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Log.d("TAG", "$t")
            }

        })
    }

    private fun setAllBooksList(books: List<Book>) {
        allBooks = books
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setBooks(books:List<Book>){
        adapter.books = books
        adapter.notifyDataSetChanged()
    }
}