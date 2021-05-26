package com.example.cleanarchitecture.core.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cleanarchitecture.core.R
import com.example.cleanarchitecture.core.databinding.ItemCardListBinding
import com.example.cleanarchitecture.core.domain.model.Showbiz
import com.example.cleanarchitecture.core.utils.ItemsChecker
import java.util.*

class ShowbizAdapter : RecyclerView.Adapter<ShowbizAdapter.ShowbizViewHolder>() {

    private var listData = ArrayList<Showbiz>()
    var onItemClick: ((Showbiz) -> Unit)? = null

    fun setData(newListData: List<Showbiz>?) {
        if (newListData == null) return
        val diffUtilCallback = ItemsChecker(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowbizViewHolder {
        val itemCardListBinding = ItemCardListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowbizViewHolder(itemCardListBinding)
    }

    override fun onBindViewHolder(holder: ShowbizViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ShowbizViewHolder(private val binding: ItemCardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Showbiz) {
            with(binding) {
                titleMedia.text = movie.title
                Glide.with(itemView.context).load(itemView.context.getString(R.string.baseUrlImage, movie.posterPath))
                    .into(image)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
