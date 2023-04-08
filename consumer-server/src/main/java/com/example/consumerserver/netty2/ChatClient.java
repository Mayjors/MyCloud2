package com.example.consumerserver.netty2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class ChatClient implements Runnable {
    private String serverIP;
    private int port;

    public ChatClient(String serverIP, int port) {
        this.serverIP = serverIP;
        this.port = port;
    }

    @Override
    public void run() {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(work);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast("encoder", new StringEncoder());
                socketChannel.pipeline().addLast("decoder", new StringEncoder());
                socketChannel.pipeline().addLast(new ClientChanelHandle());
            }
        });
        bootstrap.channel(NioSocketChannel.class);

        ChannelFuture channelFuture = bootstrap.connect(serverIP, port);
        Channel channel = channelFuture.channel();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String senMsg = scanner.nextLine();
            channel.writeAndFlush(senMsg);
        }
        work.shutdownGracefully();
    }

    public static void main(String[] args) {
        new Thread(new ChatClient("1270.0.0.1", 8090)).start();
    }
}
