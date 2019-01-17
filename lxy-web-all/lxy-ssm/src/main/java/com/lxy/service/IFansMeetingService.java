package com.lxy.service;

import com.lxy.domain.TestShow;
import java.util.List;

/**
 * Created by lxy on 14/02/2018.
 */
public interface IFansMeetingService {
    List<TestShow> getSpecialShowsAfterDesignatedDate(String date);

    TestShow getSpecialShowHasMaxShowId();

    boolean addFansMeeting(TestShow specialShow);

    boolean addFansMeetings(List<TestShow> specialShows, boolean transactional);

    boolean addFansMeetingList(List<TestShow> specialShows, boolean transactional);

    void internalLogic();
}
