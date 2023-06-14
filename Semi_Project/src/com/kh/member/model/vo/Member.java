package com.kh.member.model.vo;

import java.sql.Date;

public class Member {
	
	private int memNo; //MEM_NO	NUMBER
	private String memId; //MEM_ID	VARCHAR2(15 BYTE)
	private String memPwd; //MEM_PWD	VARCHAR2(15 BYTE)
	private String pwdQ; //PWD_Q	NUMBER
	private String pwdA; //PWD_A	VARCHAR2(100 BYTE)
	private String memName; //MEM_NAME	VARCHAR2(15 BYTE)
	private String nickname; //NICKNAME	VARCHAR2(30 BYTE)
	private String email; //EMAIL	VARCHAR2(50 BYTE)
	private String address; //ADDRESS	VARCHAR2(200 BYTE)
	private String payAccount; //PAY_ACCOUNT	VARCHAR2(30 BYTE)
	private Date enrollDate; //ENROLL_DATE	DATE
	private String memStatus; //MEM_STATUS	CHAR(1 BYTE)
	private int fileNo;
	private int refBno;

	
	public int getRefBno() {
		return refBno;
	}





	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}





	public Member(int memNo, String memId, String memPwd, String pwdQ, String pwdA, String memName, String nickname,
			String email, String address, String payAccount, Date enrollDate, String memStatus, int fileNo,
			int refBno) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memPwd = memPwd;
		this.pwdQ = pwdQ;
		this.pwdA = pwdA;
		this.memName = memName;
		this.nickname = nickname;
		this.email = email;
		this.address = address;
		this.payAccount = payAccount;
		this.enrollDate = enrollDate;
		this.memStatus = memStatus;
		this.fileNo = fileNo;
		this.refBno = refBno;
	}





	public int getFileNo() {
		return fileNo;
	}





	public Member(int memNo, String memId, String memPwd, String pwdQ, String pwdA, String memName, String nickname,
			String email, String address, String payAccount, Date enrollDate, String memStatus, int fileNo) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memPwd = memPwd;
		this.pwdQ = pwdQ;
		this.pwdA = pwdA;
		this.memName = memName;
		this.nickname = nickname;
		this.email = email;
		this.address = address;
		this.payAccount = payAccount;
		this.enrollDate = enrollDate;
		this.memStatus = memStatus;
		this.fileNo = fileNo;
	}





	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}





	public Member() {
		super();
	}





	public Member(int memNo, String memId, String memPwd, String pwdQ, String pwdA, String memName, String nickname,
			String email, String address, String payAccount, Date enrollDate, String memStatus) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memPwd = memPwd;
		this.pwdQ = pwdQ;
		this.pwdA = pwdA;
		this.memName = memName;
		this.nickname = nickname;
		this.email = email;
		this.address = address;
		this.payAccount = payAccount;
		this.enrollDate = enrollDate;
		this.memStatus = memStatus;
		
	}



	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}

	public String getPwdQ() {
		return pwdQ;
	}

	public void setPwdQ(String pwdQ) {
		this.pwdQ = pwdQ;
	}

	public String getPwdA() {
		return pwdA;
	}

	public void setPwdA(String pwdA) {
		this.pwdA = pwdA;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getMemStatus() {
		return memStatus;
	}

	public void setMemStatus(String memStatus) {
		this.memStatus = memStatus;
	}



	@Override
	public String toString() {
		return "Member [memNo=" + memNo + ", memId=" + memId + ", memPwd=" + memPwd + ", pwdQ=" + pwdQ + ", pwdA="
				+ pwdA + ", memName=" + memName + ", nickname=" + nickname + ", email=" + email + ", address=" + address
				+ ", payAccount=" + payAccount + ", enrollDate=" + enrollDate + ", memStatus=" + memStatus
				+"]";
	}

	
	
	
	

}
