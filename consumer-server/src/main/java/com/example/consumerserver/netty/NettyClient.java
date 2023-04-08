package com.example.consumerserver.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyClient extends ChannelInitializer {

    public void start(String host, Integer port) {
        //用户处理读写请求的NIO线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        //NIO服务启动辅助类
        Bootstrap bootstrap = new Bootstrap();
        //添加线程池组  不同于只需要一个处理读写请求的线程池组即可
        bootstrap.group(workerGroup)
                //添加处理读写的通道
                .channel(NioSocketChannel.class)
                //设置相关netty服务的TCP相关属性 比如发送和接受的缓冲区大小，连接超时时间
                .option(ChannelOption.TCP_NODELAY, true)
                //添加相关处理器（最终组成处理器链对请求进行处理）
                .handler(this);

        try {
            //发起异步连接请求
            ChannelFuture sync = bootstrap.connect(host, port).sync();
            //服务监听端口关闭
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭线程组
            workerGroup.shutdownGracefully();
        }

    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        //添加用户自定义的业务处理（处理自己感兴趣的）
        int msgLength = "Sun Aug 15 09:08:37 CST 2021".length();
        //1、LineBasedFrameDecoder 添加 以换行符作为消息边界编码器
        //maxLength：用于约定如果消息大于maxLength字节数 则添加换行
        //channel.pipeline().addLast("lineDecoder",new LineBasedFrameDecoder(1024));

        //2、FixedLengthFrameDecoder 使用定长消息发送消息 (定长消息对于不固定请求并不友好 使用场景很局限)
        //channel.pipeline().addLast("fixLength",
        //        new FixedLengthFrameDecoder("Sun Aug 15 09:08:37 CST 2021".length()));

        //3、DelimiterBasedFrameDecoder 消息之间添加特殊字符边界
        //ByteBuf delimter = Unpooled.copiedBuffer("$_$".getBytes());
        //channel.pipeline().addLast("delimiter",new DelimiterBasedFrameDecoder(1024,delimter));

        //4、LengthFieldBasedFrameDecoder 消息头添加消息的字段长度
        channel.pipeline().addLast("lengthFile",
                new LengthFieldBasedFrameDecoder(1024, 2, 2));

        //消息读写处理器
        channel.pipeline().addLast(new TimeClientHandler());
    }

    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        client.start("127.0.0.1", 8056);
    }
}