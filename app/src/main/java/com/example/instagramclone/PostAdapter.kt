package com.example.instagramclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class PostAdapter (val context: Context, val posts: MutableList<Post>): RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvUsername: TextView
        val ivImage: ImageView
        val tvDescription: TextView
        val tvDate: TextView

        init {
            tvUsername = itemView.findViewById(R.id.tvUserName)
            ivImage = itemView.findViewById(R.id.ivImage)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            tvDate = itemView.findViewById(R.id.tvDate)
        }

        fun bind(post: Post) {
            tvDescription.text = post.getDescription()
            tvUsername.text = post.getUser()?.username
            tvDate.text  = post.getDate()
            Glide.with(itemView.context).load(post.getImage()?.url).into(ivImage)
        }
    }

    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        // Which layout file to use for this item
        val view = LayoutInflater.from(context).inflate(R.layout.item_post,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun clear() {
        posts.clear()
        notifyDataSetChanged()
    }

    fun addAll(postsList: List<Post>) {
        posts.addAll(postsList)
        notifyDataSetChanged()
    }
}