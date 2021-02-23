package com.example.maroom.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.maroom.data.model.UserEntity
import com.example.maroom.databinding.UserLayoutBinding
import com.example.maroom.utils.BaseViewHolder

class UserAdapter(private val itemClickListener : OnUserClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var userList = listOf<UserEntity>()

    interface OnUserClickListener{
        fun onUserClick(user: UserEntity,position: Int)
        fun onUSerUpdateClick(user: UserEntity)
    }

    fun setProductList(userList: List<UserEntity>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            UserLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MainViewHolder(itemBinding, parent.context)

       itemBinding.root.setOnClickListener {
           val position =
               holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                   ?: return@setOnClickListener
           itemClickListener.onUSerUpdateClick(userList[position])
       }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(userList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    private inner class MainViewHolder(
        private val binding: UserLayoutBinding,
        var context: Context
    ) : BaseViewHolder<UserEntity>(binding.root) {
        override fun bind(item: UserEntity, position: Int) {
            binding.itemLayoutName.text = item.name
            binding.itemLayoutEmail.text = item.email
            var promedio = (item.español.toDouble() + item.matematicas.toDouble()) / 2
            binding.itemLayoutPromedio.text = promedio.toString()

            binding.btnDelete.setOnClickListener {
                adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener

                itemClickListener.onUserClick(userList[position],position)
            }

        }
    }
}