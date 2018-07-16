package xyz.tuny.jx.util;
import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;

public class Base64Util {
	// 加密
	public static String getBase64(String str) {
		byte[] b = null;
		String s = "";
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s =Base64.encodeBase64String(b);
		}
		return s;
	}

	// 解密
	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = "";
		if (s != null) {
			try {
				b =Base64.decodeBase64(s) ;
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String str="JZ";
        str=Base64Util.getBase64(str);
        System.out.println("base641:"+str);
        str = str.substring(1, str.length())+str.substring(0, 1); 
        System.out.println("base642:"+str);
		System.out.println("orient:"+Base64Util.getFromBase64(str));
		str="Slo=";
        str=Base64Util.getBase64(str);
		System.out.println("base64:"+str);
		System.out.println("orient:"+Base64Util.getFromBase64(str));
		
	}

}
