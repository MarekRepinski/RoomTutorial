package se.ctescape.roomtutorial.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_row.view.*
import se.ctescape.roomtutorial.R
import se.ctescape.roomtutorial.data.User

class ListAdapter(): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.itemView.id_txt.text = currentUser.id.toString()
        holder.itemView.firstName_txt.text = currentUser.firstName
        holder.itemView.lastName_txt.text = currentUser.lastName
        holder.itemView.age_txt.text = currentUser.age.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(l: List<User>){
        this.userList = l
        notifyDataSetChanged()
    }
}