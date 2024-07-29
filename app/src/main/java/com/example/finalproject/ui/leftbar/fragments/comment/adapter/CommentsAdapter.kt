package com.example.finalproject.ui.leftbar.fragments.comment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.dto.model.Comment
import com.example.finalproject.databinding.ItemRvFgCommentsBinding


class CommentsAdapter(private val comments : List<Comment>) : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.CommentsViewHolder {
        val binding =
            ItemRvFgCommentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentsAdapter.CommentsViewHolder, position: Int) {
        val item = comments[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = comments.size

    inner class CommentsViewHolder(private val binding: ItemRvFgCommentsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(commentsModel : Comment){
            binding.tvName.text = commentsModel.commentBy
            binding.tvComment.text = commentsModel.comment
        }
    }
}