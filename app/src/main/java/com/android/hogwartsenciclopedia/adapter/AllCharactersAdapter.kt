package com.android.hogwartsenciclopedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.hogwartsenciclopedia.databinding.GridItemCharacterBinding
import com.android.hogwartsenciclopedia.domain.CharacterModel

class CharacterAdapter(private val clickListener: CharacterClickListener) : androidx.recyclerview.widget.ListAdapter<CharacterModel, CharacterAdapter.ViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(private val binding: GridItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterModel, clickListener: CharacterClickListener) {
            binding.character = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GridItemCharacterBinding
                    .inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterModel>() {

    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem == newItem
    }
}

class CharacterClickListener(val clickListener: (characterId: String) -> Unit) {
    fun onClick(character: CharacterModel) = clickListener(character.name)
}