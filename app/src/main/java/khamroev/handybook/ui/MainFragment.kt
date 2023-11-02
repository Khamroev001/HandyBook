package khamroev.handybook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import khamroev.handybook.R
import khamroev.handybook.databinding.FragmentMainBinding
import khamroev.handybook.utils.SharedPrefHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding:FragmentMainBinding
    lateinit var sharedPrefHelper:SharedPrefHelper
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
        binding=FragmentMainBinding.inflate(inflater,container,false)
         sharedPrefHelper=SharedPrefHelper.getInstance(requireContext())
        val toggle = ActionBarDrawerToggle(requireActivity(), binding.drawerLayout, binding.toolbarDrawer, R.string.app_name, R.string.app_name)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        parentFragmentManager.beginTransaction().add(R.id.nav_view, HomeFragment())
            .commit()

        // Set up item click listener
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    parentFragmentManager.beginTransaction().replace(R.id.nav_view, HomeFragment())
                        .commit()
                }
                R.id.menu_search -> {
                    // Handle Search item click
                }
                R.id.menu_saved -> {
                    // Handle Saved item click
                }
                R.id.menu_articles -> {
                    // Handle Articles item click
                }
                R.id.menu_language -> {
                    // Handle Language item click
                }
                R.id.menu_telegram -> {
                    // Handle Telegram item click
                }
                R.id.menu_instagram -> {
                    // Handle Instagram item click
                }
                R.id.menu_logout -> {
                      showBottomSheet()
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        })


      return binding.root
    }



    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.logout_bottom_dialog, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        dialog.setContentView(bottomSheetView)

        val cancelButton: Button = bottomSheetView.findViewById(R.id.no)
        val logoutButton: Button = bottomSheetView.findViewById(R.id.yes)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        logoutButton.setOnClickListener {
            sharedPrefHelper.setUser(null)
            findNavController().navigate(R.id.action_mainFragment_to_signInFragment)
            dialog.dismiss()
        }

        dialog.show()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onBackPressed() {
//        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            binding.drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}