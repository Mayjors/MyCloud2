package com.example.consumerserver.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyServer extends ChannelInitializer {

    public void start(Integer port){
        //客户端连接NIO线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //用户处理读写请求的NIO线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        //NIO服务启动辅助类
        ServerBootstrap bootstrap = new ServerBootstrap();
        //添加线程池组 添加两个线程池组（一个线程池组用于处理客户端连接，一个线程池组用户处理读写消息）
        bootstrap.group(bossGroup,workerGroup)
                //添加处理读写的通道
                .channel(NioServerSocketChannel.class)
                //设置服务端TCP参数 SO_BACKLOG表示和该服务端连接的客户端最大数量
                .option(ChannelOption.SO_BACKLOG,1024)
                //添加相关处理器（最终组成处理器链对请求进行处理）通过ChannelPipeline添加ChannelHandler
                .childHandler(this);

        try {
            //绑定端口
            ChannelFuture sync = bootstrap.bind(port).sync();
            //服务监听端口关闭
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
    /**
     *初始化 为Channel通道的添加相关处理器 处理网络I/O事件
     */
    @Override
    protected void initChannel(Channel channel) throws Exception {
        Integer msgLength = "QUERY TIME REQUEST".length();
        //添加 以换行符作为消息边界编码器  maxLength：用于约定如果消息大雨maxLength字节数 则添加换行
        //channel.pipeline().addLast("lineDecoder",new LineBasedFrameDecoder(1024));
        //使用定长消息发送消息 (定长消息对于不固定请求并不友好 使用场景很局限)
        //channel.pipeline().addLast("fixLength",
        //          new FixedLengthFrameDecoder(msgLength));
        //消息之间添加特殊字符边界
        //ByteBuf delimter = Unpooled.copiedBuffer("$_$".getBytes());
        //channel.pipeline().addLast("delimiter",new DelimiterBasedFrameDecoder(1024,delimter));

        //4、LengthFieldBasedFrameDecoder 消息头添加消息的字段长度
        channel.pipeline().addLast("lengthFile",
                new LengthFieldBasedFrameDecoder(1024,2,2));


        channel.pipeline().addLast(new TimeServerHandler());
    }

    //服务启动
    public static void main(String[] args) {
        NettyServer server = new NettyServer();
        server.start(8056);
    }
}
