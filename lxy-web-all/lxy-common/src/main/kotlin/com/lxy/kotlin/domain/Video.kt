package com.lxy.kotlin.domain

/**
 * Created by lxy on 2017/5/26.
 */
class Video {

    var id: Int? = null

    var name: String? = null

    var url: String? = null

    constructor()

    constructor(id: Int, name: String, url: String){
        this.id = id
        this.name = name
        this.url = url
    }
}