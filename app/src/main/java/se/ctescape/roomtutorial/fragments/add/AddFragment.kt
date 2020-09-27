package se.ctescape.roomtutorial.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import se.ctescape.roomtutorial.R
import se.ctescape.roomtutorial.model.User
import se.ctescape.roomtutorial.viewmodel.UserViewModel

class AddFragment : Fragment() {
    private lateinit var mViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_add, container, false)

        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        v.update_btn.setOnClickListener {
            insertDataToDataBase()
        }

        return v
    }

    private fun insertDataToDataBase() {
        val firstName = update_firstName_et.text.toString()
        val lastName = update_lastName_et.text.toString()
        val age = update_age_et.text

        if (checkInput(firstName, lastName, age)){
            //Create user object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            //Add data to database
            mViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkInput(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}