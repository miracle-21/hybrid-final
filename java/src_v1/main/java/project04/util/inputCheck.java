package project04.util;

public class inputCheck {

	private inputCheck() {
		super();
	}
	
	public static boolean isEmpty(String str) {
		return str==null||str.isBlank();
	}
	
	public static String ifEmptyReplace(String str) {
		if(str==null||str.isBlank()) {
			str = "";
		}
		return str;
	}
	
	public static boolean isKorean(String str) {
		return str.matches("^[가-힣]*$");
	}
	
	public static boolean inLength(String str, int min, int max) {
		if(str.length() < min || str.length() > max)
			return false;
		return true;
	}
}
