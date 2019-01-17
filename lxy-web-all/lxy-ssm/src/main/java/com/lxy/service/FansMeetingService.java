package com.lxy.service;

import com.lxy.annotation.LxyProxy;
import com.lxy.dao.ISpecialShowDAO;
import com.lxy.domain.TestShow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lxy on 15/12/2017.
 */
@SuppressWarnings("ALL")
@Service
public class FansMeetingService implements IFansMeetingService {

    private static final Logger logger = LoggerFactory.getLogger(FansMeetingService.class);


    @Autowired
    private ISpecialShowDAO specialShowDAO;

    /**
     * 注入的fansMeetingService为代理对象；
     * 由于产生了自引用(循环引用)，这里只能使用@Resource，而不能使用@Autowired，
     * 否则无法正确解析自引用
     */
    @Resource
    private IFansMeetingService fansMeetingService;

    @Override
    public List<TestShow> getSpecialShowsAfterDesignatedDate(String date) {
        return specialShowDAO.getSpecialShowsAfterDesignatedDate(date);
    }

    @Override
    public TestShow getSpecialShowHasMaxShowId() {
        return specialShowDAO.getSpecialShowHasMaxShowId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addFansMeeting(TestShow specialShow) {
        return specialShowDAO.add(specialShow);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addFansMeetings(List<TestShow> specialShows, boolean transactional) {

        for (TestShow specialShow : specialShows) {
            specialShowDAO.add(specialShow);
        }
        if (transactional) {
            throw new RuntimeException("Batch Add failed!");
        }
        internalLogic();
        fansMeetingService.internalLogic();
        return true;
    }

    @Override
    @LxyProxy
    @Transactional(rollbackFor = Exception.class)
    public boolean addFansMeetingList(List<TestShow> specialShows, boolean transactional) {
        specialShowDAO.addSpecialShows(specialShows);
        if (transactional) {
            throw new RuntimeException("Batch Add failed!");
        }
        internalLogic();
        fansMeetingService.internalLogic();
        return true;
    }

    @Override
    @LxyProxy
    public void internalLogic() {
        logger.info("invoke internalLogic()");
    }

}
