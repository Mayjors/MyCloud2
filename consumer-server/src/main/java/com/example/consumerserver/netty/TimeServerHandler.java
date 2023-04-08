package com.example.consumerserver.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * 服务连接建立后 读客户端请求并响应处理类
 */
public class TimeServerHandler extends ChannelHandlerAdapter {
    /**
     * 当网络的I/O事件读事件发生 channel将事件 传递给ChannelPipeline，该对象里面的ChannelHandler链
     * 会一次对该事件进行"处理"（透传、处理、终止）此处处理逻辑获取客户端请求并发生响应
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String request = new String(bytes,"UTF-8");
        System.out.println("获取到客户端请求："+request);
        String response = request.equals("QUERY TIME REQUEST")?new Date().toString() :"BAD REQUEST";

        ctx.write(Unpooled.copiedBuffer(response.getBytes()));
    }
    /**
     * 逻辑同上当网络的I/O事件读事件完成时候触发 此处刷新缓存区到channel
     */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    /**
     * 异常发生的时候触发该方法
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}