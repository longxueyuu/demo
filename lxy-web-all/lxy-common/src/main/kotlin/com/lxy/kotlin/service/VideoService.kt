package com.lxy.kotlin.service

import com.lxy.kotlin.domain.Video

/**
 * Created by lxy on 2017/5/27.
 */
interface VideoService {
    fun getVideos(size: Int) : List<Video>
}