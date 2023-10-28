package khamroev.handybook.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import khamroev.handybook.R
import khamroev.handybook.databinding.FragmentSignInBinding
import khamroev.handybook.model.Signin
import khamroev.handybook.model.User
import khamroev.handybook.model.UserToken
import khamroev.handybook.networking.APIClient
import khamroev.handybook.networking.APIService
import khamroev.handybook.utils.SharedPrefHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var binding:FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        val api = APIClient.getInstance().create(APIService::class.java)
        var sharedPrefHelper= SharedPrefHelper.getInstance(requireContext())

        binding.homeMainBookReadNowMb.setOnClickListener {
            val signin = Signin(
                binding.signinEmail.text.toString(),
                binding.signinPasswordEdittext.text.toString()
            )
            api.login(signin).enqueue(object: Callback<UserToken> {
                override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {

                    var user= User(response.body()!!.id, response.body()!!.username,
                        response.body()!!.access_token)
                    sharedPrefHelper.setUser(user)

                    findNavController().navigate(R.id.action_signInFragment_to_mainFragment)


                }

                override fun onFailure(call: Call<UserToken>, t: Throwable) {
                    Log.d("TAG", "$t")
                }

            })
        }

          binding.donthaveaccount.setOnClickListener {
              findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
          }
          binding.loginRegisterCv.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)

          }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignInFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignInFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}