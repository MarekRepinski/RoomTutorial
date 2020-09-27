package se.ctescape.roomtutorial.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import se.ctescape.roomtutorial.R
import se.ctescape.roomtutorial.model.User

class ListAdapter(): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var personPosition = 0

        init {
            itemView.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(userList[personPosition])
                itemView.findNavController().navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.firstName_txt.text = currentItem.firstName
        holder.itemView.lastName_txt.text = currentItem.lastName
        holder.itemView.age_txt.text = currentItem.age.toString()
        holder.personPosition = position
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(l: List<User>){
        this.userList = l
        notifyDataSetChanged()
    }
}