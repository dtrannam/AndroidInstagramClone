package com.example.instagramclone.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.instagramclone.Post
import com.example.instagramclone.PostAdapter
import com.example.instagramclone.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

open class FeedFragment : Fragment() {
    lateinit var postsRecyclerView: RecyclerView
    lateinit var adapter: PostAdapter
    lateinit var swipeContainer: SwipeRefreshLayout
    var allPosts: MutableList<Post> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set Up Logics
        postsRecyclerView = view.findViewById(R.id.postRecyclerView)
        adapter = PostAdapter(requireContext(), allPosts)
        postsRecyclerView.adapter = adapter
        postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        // Gets Posts
        queryPosts()

        // Sets up Refresh
        swipeContainer = view.findViewById(R.id.swipeContainer)
        swipeContainer.setOnRefreshListener {
            queryPosts()
        }
    }

    open fun queryPosts() {
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        // Find all Post
        query.include(Post.KEY_USER)
        query.addDescendingOrder("createdAt")
        query.setLimit(20)
        query.findInBackground(object: FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    Log.e(TAG, "Error fetching Posts")
                } else {
                    if (posts != null) {
                        adapter.clear()
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                        swipeContainer.setRefreshing(false)
                    }
                }
            }

        })
    }

    companion object {
        val TAG = "Feed Page"
    }

    open fun queryPost() {}
}