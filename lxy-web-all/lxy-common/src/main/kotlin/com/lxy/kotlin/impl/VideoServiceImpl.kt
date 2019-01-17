package com.lxy.kotlin.impl

import com.lxy.kotlin.domain.Video
import com.lxy.kotlin.service.VideoService
import java.util.*

/**
 * Created by lxy on 2017/5/27.
 */
class VideoServiceImpl : VideoService{

    override fun getVideos(size: Int) : List<Video>{

        var videos: List<Video> = ArrayList<Video>()

        for(i in 1..size)
        {
//            println("video i:${i}")
            var video: Video = Video()
            video.id = i
            video.name = "lxy-${i}"
            video.url = "${i}.lxy.com"
            videos = videos.plus(video)
        }
//        println("videos size:${videos.size}")
        return videos
    }
}