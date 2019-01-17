package com.lxy.exception;

import com.lxy.common.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lxy on 08/01/2018.
 */
// @Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        User user = new User();
        user.setUid("111");
        user.setName("lxy");
        user.setAge(29);
        mv.addObject("user", user);
        mv.addObject("exception", ex);
        mv.setViewName("forward:/exception/test.htm");
        return mv;
    }
}
