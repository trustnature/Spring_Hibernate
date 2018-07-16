package xyz.tuny.jx.server;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import xyz.tuny.jx.service.IRzlogService;
import xyz.tuny.jx.util.ApplicationContextHelper;
import xyz.tuny.jx.util.Encrypt;

import java.nio.charset.Charset;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/**
 * 业务线程
 */
public class MessageRecvInitializeTask implements Runnable {
	private Logger log = Logger.getLogger(MessageRecvInitializeTask.class);
	private String request = null;
    private ChannelHandlerContext ctx = null;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    MessageRecvInitializeTask(String request, ChannelHandlerContext ctx) {
        this.request = request;
        this.ctx = ctx;
    }

    public void run() {
        try {     	
        	long start = System.currentTimeMillis();
        	//先对称解密  时间取决于数据大小
        	String paramXml = Encrypt.decrypt(request);
        	log.info("Server Decrypt:"+ (System.currentTimeMillis()-start) + "ms\n" + paramXml );
        	//服务分发
        	String sv=getSv(paramXml);
            String res="";
            if(StringUtils.isNotBlank(sv)){
            	if("fprz".equals(sv)){
            		IRzlogService cs = (IRzlogService)ApplicationContextHelper.getBean("rzlogServiceImpl");//登录          
            		res = cs.saveOrUpdate(paramXml);
            	}else
            		res="<?xml version=\"1.0\" encoding=\"GBK\"?><rd><fl>0</fl><msg>不存在该服务类型</msg></rd>";
            }else
            	res="<?xml version=\"1.0\" encoding=\"GBK\"?><rd><fl>0</fl><msg>服务类型参数不正确</msg></rd>";
    		//对称加密 时间忽略不计
            res=Encrypt.encrypt(res);//返回前对称加密
    		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,  
                    HttpResponseStatus.OK, Unpooled.copiedBuffer(res,Charset.forName("GBK")));  
            response.headers().set(CONTENT_TYPE, "text/xml; charset=GBK");  
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE); 
            long end = System.currentTimeMillis();
            log.info("Server Thread:"+sv+":" + (end - start) + "ms" );
        } catch (Throwable t) {
            t.printStackTrace();
            System.err.printf("Server invoke error!\n");
        }     
    }
    
    public String getSv(String paramXml){
	    	int beginI = paramXml.indexOf("<sv>"); 
			int endI =paramXml.indexOf("</sv>"); 
			if(beginI<0||endI<0) return "";
			return  paramXml.substring(beginI + 4, endI);	
    }
}
