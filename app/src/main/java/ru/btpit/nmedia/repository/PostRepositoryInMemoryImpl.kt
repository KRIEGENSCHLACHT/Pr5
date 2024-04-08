package ru.btpit.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.btpit.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository{
    private var post = Post(
            id = 1,
            author = "КД-Саунд - мастерская звукоаппаратуры",
            content = "Купили дом, завезли красивую мебель, приобрели навороченную плазму - а звука нет? Тогда вам стоит посетить КД-Саунд в Борисоглебске! Мы делаем Hi-Fi оборудование на заказ на территории Борисоглебска, возможна доставка по области",
            published = "25 марта в 13:01",
            likedByMe = false,
            likes = 999999,
            share = 999
        )
    private val data = MutableLiveData(post)
    override fun get(): LiveData<Post> = data
    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe)
        if (post.likedByMe) post.likes++ else post.likes--
        data.value = post
    }
    override fun share() {
        post.share++
        data.value = post
    }
}


