package ru.btpit.nmedia.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.btpit.nmedia.databinding.ActivityMainBinding

import androidx.activity.viewModels
import ru.btpit.nmedia.R


import ru.btpit.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likeCount.text = post.likes.toString()
                shareCount.text = post.share.toString()
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                likeCount.text = post.likes.toString()
                when {
                    post.likes in 1000..999999 -> likeCount.text = "${post.likes / 1000}K"
                    post.likes < 1000 -> likeCount.text = post.likes.toString()
                    else -> likeCount.text = String.format("%.1fM", post.likes.toDouble() / 1000000)
                }
                shareCount.text = post.share.toString()
                when {
                    post.share < 1000 -> shareCount.text = post.share.toString()
                    post.share in 1000..999999 -> shareCount.text = "${post.share / 1000}K"
                    else -> shareCount.text = String.format(
                        "%.1fM", post.share.toDouble() / 1000000
                    )
                }
            }
            binding.like.setOnClickListener {
                viewModel.like()
            }
            binding.share.setOnClickListener {
                viewModel.share()
            }
        }
    }
}






