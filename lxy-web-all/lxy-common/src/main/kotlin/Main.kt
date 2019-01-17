import com.lxy.kotlin.domain.Video
import com.lxy.kotlin.impl.VideoServiceImpl
import com.lxy.kotlin.service.VideoService

/**
 * Created by lxy on 2017/5/26.
 */

fun main(args: Array<String>){
    var video: Video = Video(2, "加勒比海盗", "www.lxy.com")
    println("${video.id} ${video.name} ${video.url}")

    for(i in 1..10)
    {
        println(i)
    }

    var flag: Boolean? = null

    if(flag == true)
    {
        println("$flag")
    }else{
        println("$flag")
    }

    // Hypothetical code, does not actually compile:
//    val a: Int? = 1
//    val b: Long? = a

    var c: Char = '9'
    var cint: Int = c.toInt() - '0'.toInt()
    println("'9' to int: ${cint}")

    var intArr: IntArray = intArrayOf(1, 2, 3)
    intArr = intArrayOf(2, 2, 3)
    for(item in intArr)
    {
        println("item: $item")
    }

    var str: String = """
        >abc\n
        >hahaha
        """.trimMargin(">")
    println(str)

    var videoService: VideoService = VideoServiceImpl()
    var videos: List<Video> = videoService.getVideos(10)
    for (video: Video in videos)
    {
        println("${video.id} ${video.name} ${video.url}")
    }

}