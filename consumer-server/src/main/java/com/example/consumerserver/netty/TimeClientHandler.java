package com.example.consumerserver.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String response = new String(bytes,"UTF-8");
        System.out.println("获取到服务端响应："+response);
    }

    /**
     * 与服务端建立TCP连接成功后触发该方法（处理逻辑:发送客户端请求） 其他类似
     *  此处发送多个请求 模拟服务出现粘包拆包现象发生
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //使用LineBaseFrameDecoder编码器
        //String msg = "QUERY TIME REQUEST"+System.getProperty("line.separator");
        //模拟定长请求
        String msg = "QUERY TIME REQUEST";
        //特殊字符作为边界
        //String msg = "QUERY TIME REQUEST$_$";
        byte[] request = msg.getBytes();
        //发送多次请求 复现tcp的沾包和拆包
        for(int i=0;i<100;i++){
            ByteBuf buffer = Unpooled.buffer(request.length);
            buffer.writeBytes(request);
            ctx.writeAndFlush(buffer);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
