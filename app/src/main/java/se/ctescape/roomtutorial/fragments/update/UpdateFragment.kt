package se.ctescape.roomtutorial.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import se.ctescape.roomtutorial.R
import se.ctescape.roomtutorial.model.User
import se.ctescape.roomtutorial.viewmodel.UserViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        v.update_firstName_et.setText(args.currentUser.firstName)
        v.update_lastName_et.setText(args.currentUser.lastName)
        v.update_age_et.setText(args.currentUser.age.toString())

        v.update_btn.setOnClickListener {
            updateItem()
        }

        //Add menu
        setHasOptionsMenu(true)

        return v
    }

    private fun updateItem(){
        val firstName = update_firstName_et.text.toString()
        val lastName = update_lastName_et.text.toString()
        val age = Integer.parseInt(update_age_et.text.toString())

        if (checkInput(firstName, lastName, update_age_et.text)){
            //Create User Object
            val updatedUser = User(args.currentUser.id, firstName, lastName, age)
            //Update current user
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Successfully Updated User", Toast.LENGTH_LONG).show()
            //Nav back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkInput(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(requireContext(), "Successfully removed ${args.currentUser.firstName}", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("N0"){_, _ ->}
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}