package com.example.algorithm.design;

public class AuthHandler extends Handler {

    @Override
    public void doHandler(Member member) {
        if (!"管理员".equals(member.getRoleName())) {
            System.out.println("您不是管理员，没有操作权限");
            return;
        }
        if (null != next) {
            next.doHandler(member);
        }
    }
}
