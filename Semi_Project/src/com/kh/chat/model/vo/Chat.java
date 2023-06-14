package com.kh.chat.model.vo;

import java.util.Date;

public class Chat {
	
	private int chatNo;//CHAT_NO	NUMBER 채팅번호 
	private int roomNo;//ROOM_NO	NUMBER 채팅방번호
	private String chatContent;//CHAT_CONTENT	VARCHAR2(1000 BYTE) 채팅 내용
	private int memNo;//MEM_NO	NUMBER 채팅방멤버번호
	private String chatTime;//CHAT_TIME		DATE 채팅 시간
	private Date createDate;//CREATE_DATE	DATE 방생성 날짜
	private String alarmYN;//ALARM_YN	CHAR(1 BYTE) 알람여부
	private String chatType;//CHAT_TYPE	CHAR(1 BYTE) 채팅 타입 (N,P)
	private String roomState;//ROOM_STATE	CHAR(1 BYTE) 방삭제 여부(한쪽만 N이여도 삭제)
	private int productNo;//PRODUCT_NO	NUMBER 상품번호
	private String productName; //PRODUCT_NAME 상품이름
	private int productPrice; //PRODUCT_PRICE 상품 가격
	private String nickName; // 닉네임
	private int Count; 
	private int seller; // 판매자
	
	
	public int getSeller() {
		return seller;
	}
	public void setSeller(int seller) {
		this.seller = seller;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Chat() {
		super();
	}
	public Chat(int chatNo, int roomNo, String chatContent, int memNo, String chatTime, Date createDate,
			String alarmYN, String chatType, String roomState, int productNo, String productName, int productPrice) {
		super();
		this.chatNo = chatNo;
		this.roomNo = roomNo;
		this.chatContent = chatContent;
		this.memNo = memNo;
		this.chatTime = chatTime;
		this.createDate = createDate;
		this.alarmYN = alarmYN;
		this.chatType = chatType;
		this.roomState = roomState;
		this.productNo = productNo;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	public int getChatNo() {
		return chatNo;
	}
	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAlarmYN() {
		return alarmYN;
	}
	public void setAlarmYN(String alarmYN) {
		this.alarmYN = alarmYN;
	}
	public String getChatType() {
		return chatType;
	}
	public void setChatType(String chatType) {
		this.chatType = chatType;
	}
	public String getRoomState() {
		return roomState;
	}
	public void setRoomState(String roomState) {
		this.roomState = roomState;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	@Override
	public String toString() {
		return "Chat [chatNo=" + chatNo + ", roomNo=" + roomNo + ", chatContent=" + chatContent + ", memNo=" + memNo
				+ ", chatTime=" + chatTime + ", createDate=" + createDate + ", alarmYN=" + alarmYN + ", chatType="
				+ chatType + ", roomState=" + roomState + ", productNo=" + productNo + ", productName=" + productName
				+ ", productPrice=" + productPrice + "]";
	}
}
