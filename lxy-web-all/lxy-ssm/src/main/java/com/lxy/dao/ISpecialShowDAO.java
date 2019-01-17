package com.lxy.dao;

import com.lxy.domain.TestShow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lxy on 17/8/17.
 */
public interface ISpecialShowDAO {

    @Select("SELECT * FROM test_show WHERE id=#{id}")
    TestShow query(int id);

    @Select("SELECT * FROM test_show WHERE cinema_id=#{cinemaId} AND expire_time > NOW()")
    List<TestShow> queryEffectiveSpecialShowsByCinemaId(int cinemaId);

    @Select("SELECT * FROM test_show WHERE city_id=#{cityId} AND expire_time > NOW()")
    List<TestShow> queryEffectiveSpecialShowsByCityId(int cityId);

    @Select("SELECT * FROM test_show WHERE date >= #{date} AND type = 1 AND status = 0")
    List<TestShow> getSpecialShowsAfterDesignatedDate(@Param("date") String date);

    @Select("SELECT * FROM test_show order by show_id desc limit 0, 1")
    TestShow getSpecialShowHasMaxShowId();

    @Insert("insert into test_show " +
            " (show_id, date, city_id, cinema_id, movie_id, movie_name, expire_time, type, meeting_type, guest, time_limit, label, status) " +
            " values (#{showId}, #{date}, #{cityId}, #{cinemaId}, #{movieId}, #{movieName}, " +
            " #{expireTime}, #{type}, #{meetingType}, #{guest}, #{timeLimit}, #{label}, #{status})")
    boolean add(TestShow specialShow);


    @Insert({"<script>",
            "insert into test_show (show_id, date, city_id, cinema_id, movie_id, movie_name, expire_time, type, meeting_type, guest, time_limit, label, status) values " +
                    "<foreach collection='specialShows' index='index' item='s' separator='),(' open='(' close=')'> " +
                    " #{s.showId}, #{s.date}, #{s.cityId}, #{s.cinemaId}, #{s.movieId}, #{s.movieName}, " +
                    " #{s.expireTime}, #{s.type}, #{s.meetingType}, #{s.guest}, #{s.timeLimit}, #{s.label}, #{s.status} " +
                    "</foreach>",
            "</script>"})
    boolean addSpecialShows(@Param("specialShows") List<TestShow> specialShows);

}
