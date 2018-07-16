package xyz.tuny.jx.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Encrypt {
	private static final String PASSWORD_CRYPT_KEY = "F@P#C$Y%NIU";
	private static final String DES = "DES";

	private static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();

		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance("DES");

		cipher.init(1, securekey, sr);

		return cipher.doFinal(src);
	}

	private static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();

		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance("DES");

		cipher.init(2, securekey, sr);

		return cipher.doFinal(src);
	}

	public static final String decrypt(String data) {
		try {
			return new String(decrypt(hex2byte(data.getBytes("GBK")),
					PASSWORD_CRYPT_KEY.getBytes("GBK")),"GBK");
		} catch (Exception localException) {
		}
		return "";
	}

	public static final String encrypt(String str) {
		try {
			return byte2hex(encrypt(str.getBytes("GBK"),
					PASSWORD_CRYPT_KEY.getBytes("GBK")));
		} catch (Exception localException) {
		}
		return "";
	}

	private static String byte2hex(byte[] b) {
		String hs = "";

		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);

			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}

		return hs.toUpperCase();
	}

	private static byte[] hex2byte(byte[] b) {
		if (b.length % 2 != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}
		byte[] b2 = new byte[b.length / 2];

		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);

			b2[(n / 2)] = ((byte) Integer.parseInt(item, 16));
		}

		return b2;
	}
	
	private static String byteToArrayString(byte bByte, String[] strDigits) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	private static String byteToString(byte[] bByte, String[] strDigits) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i], strDigits));
		}
		return sBuffer.toString();
	}

	public static String GetMD5Code(String strObj, String[] strDigits) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");

			resultString = byteToString(md.digest(strObj.getBytes("GBK")),
					strDigits);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return resultString;
	}
	
	public static String md5(String afeax)
	  {
	    String fffO = "";
	    for (int a = 0; a < 16; a++) {
	      fffO = fffO + "-" + Integer.toHexString(a);
	    }
	    fffO = fffO.substring(1);
	    String fff0 = fffO;
	    char aa = '2';
	    fff0 = fff0.replace(aa, '+');
	    fff0 = fff0.replace('b', '=');
	    fff0 = fff0.replace('+', 'b');
	    fff0 = fff0.replace('=', aa);
	    fff0 = fff0.replace('6', 'A');
	    fff0 = fff0.replace('8', '@');
	    fff0 = fff0.replace('A', '8');
	    fff0 = fff0.replace('@', '6');
	    String strmd5 = GetMD5Code(afeax.toLowerCase(), fff0.split("-"));
	    String str = GetMD5Code(strmd5, fffO.split("-"));
	    return str;
	  }
	
}