package com.example.consumerserver.netty2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

public class ChatServer {
    public void openSrver(int port) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup(5);
        bootstrap.group(boss, work)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast("encoder", new StringEncoder());
                        socketChannel.pipeline().addLast("decoder", new StringEncoder());
                        socketChannel.pipeline().addLast(new ServerChanelHandle());
                    }
                });
        bootstrap.channel(NioServerSocketChannel.class);
        try {
            ChannelFuture channel = bootstrap.bind(port).sync();
            System.out.println("服务端已启动，绑定端口：" + port);
            channel.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.openSrver(8090);
    }
}
