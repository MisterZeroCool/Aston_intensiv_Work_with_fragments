package ru.agavrilyuk.aston_intensiv_work_with_fragments.task_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.agavrilyuk.aston_intensiv_work_with_fragments.R
import ru.agavrilyuk.aston_intensiv_work_with_fragments.databinding.ListItemBinding


class UsersAdapter(
    private val listener: Listener
) : ListAdapter<User, UsersAdapter.Holder>(Comparator()) {
    class Holder(
        view: View,
        private val listener: Listener
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)

        fun bind(user: User) = with(binding) {
            val t = "${user.name} ${user.lastName}"
            imageView.setImageResource(user.im)
            title.text = t
            val number = "Tel: ${user.phoneNumber}"
            tel.text = number
            itemView.setOnClickListener {
                listener.onClick(user.copy(id = adapterPosition))
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.im == newItem.im
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return Holder(view, listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    interface Listener {
        fun onClick(user: User)
    }
}