package com.lxy.service;

import com.lxy.annotation.Dispatch;
import com.lxy.domain.GeneralRequestParam;
import org.springframework.stereotype.Service;

/**
 * Created by lxy on 04/12/2017.
 */
@Service
public class LoginService {

    @Dispatch
    public String exist(GeneralRequestParam generalRequestParam) {

        if (generalRequestParam.isDispatch()) {
            return generalRequestParam.getUserId() + "-" + generalRequestParam.getName() + " I'm born!";
        } else {
            return generalRequestParam.getUserId() + "-" + generalRequestParam.getName() + " I'm gone!";
        }
    }

    public String add(GeneralRequestParam generalRequestParam) {
        if (generalRequestParam.isDispatch()) {
            return generalRequestParam.getUserId() + "-" + generalRequestParam.getName() + " I'm born!";
        } else {
            return generalRequestParam.getUserId() + "-" + generalRequestParam.getName() + " I'm gone!";
        }
    }
}
