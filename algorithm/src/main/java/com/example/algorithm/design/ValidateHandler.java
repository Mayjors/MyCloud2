package com.example.algorithm.design;

import org.apache.commons.lang.StringUtils;

public class ValidateHandler extends Handler {

    @Override
    public void doHandler(Member member) {
        if (StringUtils.isEmpty(member.getUsername()) || StringUtils.isEmpty(member.getPassword())) {
            System.out.println("用户名和密码不能为空！");
            return;
        }
        if (null != next) {
            next.doHandler(member);
        }
    }
}
