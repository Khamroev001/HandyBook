package khamroev.handybook.ui

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import khamroev.handybook.R
import khamroev.handybook.databinding.FragmentSignUpBinding
import khamroev.handybook.model.SignUp
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
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
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
    lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSignUpBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
         var sharedPrefHelper= SharedPrefHelper.getInstance(requireContext())


        val api = APIClient.getInstance().create(APIService::class.java)
        binding.sighupButton.setOnClickListener {
            if (check()){
                val signUp = SignUp(
                    binding.signupNameEdittext.text.toString(),
                    binding.signupSurnameEdittext.text.toString(),
                    binding.signupEmailEdittext.text.toString(),
                    binding.signupPasswordEdittext.text.toString()
                )
                api.signup(signUp).enqueue(object: Callback<UserToken> {
                    override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
                       var user= User(response.body()!!.id, response.body()!!.username,
                           response.body()!!.access_token)
                       sharedPrefHelper.setUser(user)


                        Log.d("USER",user.toString())
                        findNavController().navigate(R.id.action_signUpFragment_to_mainFragment)
                    }

                    override fun onFailure(call: Call<UserToken>, t: Throwable) {
                        Log.d("ERROR",t.toString())
                    }

                })
            }


        }




        return binding.root
    }
      fun check(): Boolean {
          if (!binding.ischecked.isChecked){
              Toast.makeText(requireContext(),"Tasdiqlashni bosing",Toast.LENGTH_SHORT).show()
              return false
          }
          if (binding.signupPasswordEdittext.text.toString()!=(binding.signupPasswordRecheckEdittext.text.toString())) {
              Toast.makeText(requireContext(),"Parollar mos kelmayapti",Toast.LENGTH_SHORT).show()
              binding.signupPasswordEdittext.error="Parollar mos kelmayapti"
              binding.signupPasswordRecheckEdittext.error="Parollar mos kelmayapti"
              return false
          }
          if (binding.signupPasswordEdittext.text!!.count()<8 && binding.signupPasswordRecheckEdittext.text!!.count()<8){
              Toast.makeText(requireContext(),"Parol 8 ta belgidan kam bolmasligi kerak",Toast.LENGTH_SHORT).show()
              binding.signupPasswordEdittext.error="Parol 8 ta belgidan kam bolmasligi kerak"
              binding.signupPasswordRecheckEdittext.error="Parol 8 ta belgidan kam bolmasligi kerak"
              return false
          }
          if(!Patterns.EMAIL_ADDRESS.matcher(binding.signupEmailEdittext.text.toString()).matches()){
              Toast.makeText(requireContext(),"Email hato",Toast.LENGTH_SHORT).show()
              binding.signupEmailEdittext.error="Emailni to'g'ri kiriting"
              return false
          }
          return true
      }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}