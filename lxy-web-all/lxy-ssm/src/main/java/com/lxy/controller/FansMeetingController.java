package com.lxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.lxy.domain.TestShow;
import com.lxy.service.IFansMeetingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by lxy on 15/12/2017.
 */

@Controller
@RequestMapping("fansmeeting")
public class FansMeetingController {

    @Autowired
    private IFansMeetingService fansMeetingService;

    @RequestMapping("after")
    @ResponseBody
    public String getSpecialShowsAfterDesignatedDate(String date) {
        JSONObject json = new JSONObject();
        List<TestShow> specialShows = fansMeetingService.getSpecialShowsAfterDesignatedDate(date);

        json.put("code", 0);
        json.put("total", specialShows.size());
        json.put("specialShows", specialShows);

        return json.toString();
    }


    @RequestMapping("add")
    @ResponseBody
    public String addFansMeeting(TestShow specialShow) {
        JSONObject json = new JSONObject();
        boolean isSuccess = fansMeetingService.addFansMeeting(specialShow);
        json.put("code", 0);
        json.put("success", isSuccess);
        return json.toString();
    }


    @RequestMapping("addBatch")
    @ResponseBody
    public String addFansMeetingBatch(int count, boolean transactional) {

        JSONObject json = new JSONObject();
        List<TestShow> specialShows = getSpecialShowList(count);

        boolean isSuccess = false;
        try {
            isSuccess = fansMeetingService.addFansMeetings(specialShows, transactional);
        } catch (Exception e) {
            json.put("exception", e.toString());
        }

        json.put("code", 0);
        json.put("success", isSuccess);
        return json.toString();
    }

    @RequestMapping("addList")
    @ResponseBody
    public String addFansMeetingList(int count, boolean transactional) {
        JSONObject json = new JSONObject();
        List<TestShow> specialShows = getSpecialShowList(count);

        boolean isSuccess = false;
        try {
            isSuccess = fansMeetingService.addFansMeetingList(specialShows, transactional);
        } catch (Exception e) {
            json.put("exception", e.toString());
        }

        json.put("code", 0);
        json.put("success", isSuccess);
        return json.toString();
    }


    private List<TestShow> getSpecialShowList(int count) {
        TestShow specialShow = fansMeetingService.getSpecialShowHasMaxShowId();

        List<TestShow> specialShows = new ArrayList<>();
        IntStream.range(1, count + 1).forEach((i) -> {
            TestShow specialShowTemp = new TestShow();
            BeanUtils.copyProperties(specialShow, specialShowTemp);
            specialShowTemp.setShowId(specialShowTemp.getShowId() + i);
            specialShows.add(specialShowTemp);
        });
        return specialShows;
    }
}
