package vo;
/*
CREATE DATABASE CONO;

CREATE TABLE member (
	id VARCHAR(16) PRIMARY KEY,
 	pass VARCHAR(16) NOT NULL,
 	email VARCHAR(50) UNIQUE NOT NULL,
 	nick VARCHAR(14) NOT NULL,
 	phone VARCHAR(20) NOT NULL
);
 
 */


public class MemberDTO {
	private String id;
	private String pass;
	private String email;
	private String nick;
	private String phone;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}










