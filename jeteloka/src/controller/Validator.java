package controller;

public class Validator {

	public boolean rentTime(int rentTime) {
		return (rentTime >= 1 && rentTime <= 60) ? true : false;
	}
	
	public boolean userName(String userName) {
		return (userName.length() >= 1 && userName.length() <= 15) ? true : false;
	}
	
	public boolean email(String email) {
		return (email.endsWith("@gmail.com")) ? true : false;
	}
	
	public boolean phone(String phone) {
		return (phone.length() >= 10 && phone.length() <= 13) ? true : false;
	}
}
