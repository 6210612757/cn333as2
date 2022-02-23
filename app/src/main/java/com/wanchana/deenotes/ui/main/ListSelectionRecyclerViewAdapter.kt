package com.wanchana.deenotes.ui.main

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wanchana.deenotes.databinding.ListSelectionViewHolderBinding
import com.wanchana.deenotes.models.Noted

class ListSelectionRecyclerViewAdapter(private val lists: MutableList<Noted>,
                                       private val clickListener: ListSelectionRecyclerViewClickListener,
                                       private val holdClickListener: ListSelectionRecyclerViewClickListener
                                       ) : RecyclerView.Adapter<ListSelectionViewHolder>() {

    interface ListSelectionRecyclerViewClickListener {
        fun listItemClicked(list: Noted)
        fun listItemHold(list: Noted)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return ListSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.binding.itemNumber.text = (position + 1).toString()
        holder.binding.itemName.text = lists[position].name
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }
        holder.itemView.setOnLongClickListener{
            holdClickListener.listItemHold(lists[position])
            true
        }
    }

    override fun getItemCount() = lists.size

    fun listsUpdated() {
        Log.d(ContentValues.TAG, lists.size.toString())
        notifyItemInserted(lists.size-1)
    }
    fun listsRemove(posRemove : Int){
        Log.d(ContentValues.TAG, lists.size.toString())
        notifyItemRemoved(posRemove)

    }
}
