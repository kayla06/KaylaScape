/**
 * Copyright (c) 2015 Kyle Friz & Kayla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.kayla;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.kayla.network.protocol.GameChannelHandler;
import org.kayla.openrs.Cache;
import org.kayla.openrs.FileStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kyle Friz
 * @author Kayla
 * @date Apr 9, 2015
 */
public class Server {

	private static Logger logger = LoggerFactory.getLogger(Server.class);
	
	private static Cache cache;
	private static ByteBuffer table;
    private static NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    private static NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    private static ChannelFuture future;
	
	public static void main(String[] agrs) {
		try {
			cache = new Cache(FileStore.open(Constants.CACHE_REPOSITORY));
			table = cache.createChecksumTable().encode();
			
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {

					@Override
					protected void initChannel(NioSocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						pipeline.addLast(new GameChannelHandler());
					}
					
				})
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.TCP_NODELAY, true);
			
			try {
				future = bootstrap.bind(43594).sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("Bound " + Constants.FRAME_NAME + " on: " + future.channel().localAddress().toString());
		
		} catch (IOException e) {
			logger.error("Error starting " + Constants.FRAME_NAME + "!", e);
		}
	}
	
	public static void shutdown() {
		try {
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			logger.error("Error shutting down!", e);
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static Cache cache() {
		return cache;
	}
	
	public static ByteBuffer table() {
		return table;
	}
	
}
