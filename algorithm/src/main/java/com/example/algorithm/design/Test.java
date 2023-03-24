package com.example.algorithm.design;

public class Test {
    public static void main(String[] args) {
        Handler.Builder builder = new Handler.Builder();
        builder.addHandler(new ValidateHandler())
                .addHandler(new LoginHandler())
                .addHandler(new AuthHandler());
        Member member = new Member();
        member.setUsername("");
        member.setPassword("");

        builder.build().doHandler(member);
    }
}
