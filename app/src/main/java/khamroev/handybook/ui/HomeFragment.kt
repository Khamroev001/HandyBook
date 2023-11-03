package khamroev.handybook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import khamroev.handybook.R
import khamroev.handybook.adapter.CategoryAdapter
import khamroev.handybook.databinding.FragmentHomeBinding
import khamroev.handybook.model.Category
import khamroev.handybook.networking.APIClient
import khamroev.handybook.networking.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentHomeBinding
    lateinit var api: APIService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        api = APIClient.getInstance().create(APIService::class.java)









//        binding.homeCategoryRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        api.getCategories().enqueue(object : Callback<List<Category>> {
//            override fun onResponse(
//                call: Call<List<Category>>,
//                response: Response<List<Category>>
//            ) {
//                if (!response.isSuccessful) return
//                binding.homeCategoryRecycler.adapter = CategoryAdapter(response.body()!!, requireContext(), binding.homeCategoryRecycler, object : CategoryAdapter.CategoryPressed{
//                    override fun onPressed(category: String?) {
//                        if (category == null){
//                            setAllBooks(allBooks)
//                            binding.homeMainBookContainer.visibility = View.VISIBLE
//                            return
//                        }
//                        binding.homeMainBookContainer.visibility = View.GONE
//                        setCategoryChanger(category)
//                    }
//                })






        // Inflate the layout for this fragment
        return binding.root
    }
}