package encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
// 12/12 암호화 작업
public class MyMessageDigest {

	private String hashAlgorithm = ""; // 해싱 알고리즘명 저장할 변수

	// 파라미터 생성자 정의
	// -> 암호화 알고리즘명 전달받아 초기화
	public MyMessageDigest(String hashAlgorithm) {
		this.hashAlgorithm = hashAlgorithm;
	}
	
	// 해싱(= 단방향 암호화) 작업을 수행할 hashing() 정의
	public String hasing(String strPlainText) {
		String strCipherText = "";
		
		try {
			
			MessageDigest md = MessageDigest.getInstance(hashAlgorithm); // 1
			
			byte[] byteData = strPlainText.getBytes(); // 2
			System.out.println(Arrays.toString(byteData));
			
			md.update(byteData); // 3
			
			byte[] digestResult = md.digest(); // 4
			System.out.println(Arrays.toString(digestResult));
			
			for(int i = 0; i < digestResult.length; i++) { // 5
				// int타입 정수를.16진수.대문자로.
				strCipherText += Integer.toHexString(digestResult[i] & 0xFF).toUpperCase();
			}
			
			System.out.println(strCipherText);
			
		} catch (NoSuchAlgorithmException e) {
			System.out.println(hashAlgorithm + " 알고리즘이 존재하지 않습니다!");
			e.printStackTrace();
		}
		
		return strCipherText;
	}
	
}
