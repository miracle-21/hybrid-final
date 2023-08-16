package project04;

import project04.util.inputCheck;
import project04.vo.Account;

public class main {

	public static void main(String[] args) {
		DAO dao = new DAO();
		Account a1 = dao.getAccountNo(1);
		System.out.println(a1.getBirthday());
		System.out.println(dao.login("asdasd123", "123123"));
		String str1 = "메로나";
		String str2 = null;
		str2 = inputCheck.ifEmptyReplace(str2);
		System.out.println(inputCheck.isKorean(str1));
		System.out.println(str2);
		String str3 = "45444123";
		System.out.println(str3.chars().allMatch(Character::isDigit));
		System.out.println(str3.matches("^[a-zA-Z][a-zA-Z!@#$%^&*()_+=<>?0-9]{9,20}$"));
		String str4 = "asdasd123@naver.com";
		System.out.println(str4.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"));
	}
}
