package xyz.tuny.jx.util;

public class TestDecrypt {
	public static void main(String[] args) {
		String request="E8E378DB8FE88338";
		String paramXml = Encrypt.decrypt(request);
		System.out.println(paramXml);
		
		String crypt = "<sv>fprz</sv><qysh>371636544435</qysh><qymc>山东</qymc><sqm>33444</sqm><datetime>2018-01-01 12:12:20</datetime>";
		String cryptText = Encrypt.encrypt(crypt);
		System.out.println(cryptText);
	}
}
