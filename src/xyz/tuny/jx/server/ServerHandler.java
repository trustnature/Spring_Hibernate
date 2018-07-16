package xyz.tuny.jx.server;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.Names.LOCATION;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

/**
 * netty 事件处理
 */
public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
	 
	@Override
	protected void messageReceived(ChannelHandlerContext ctx,FullHttpRequest request) throws Exception {
		ByteBuf buf =  request.content();  
        byte [] req = new byte[buf.readableBytes()];    
        buf.readBytes(req);    
        String requestStr = new String(req,"GBK");    
        String response = "";
        MessageRecvInitializeTask recvTask = new MessageRecvInitializeTask(requestStr, ctx);
        MessageRecvExecutor.submit(recvTask);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		if (ctx.channel().isActive()) {
			sendError(ctx, INTERNAL_SERVER_ERROR);
		}
	}

	private static void sendError(ChannelHandlerContext ctx,
			HttpResponseStatus status) {
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
				status, Unpooled.copiedBuffer("Failure: " + status.toString()
						+ "\r\n", CharsetUtil.UTF_8));
		response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

}
