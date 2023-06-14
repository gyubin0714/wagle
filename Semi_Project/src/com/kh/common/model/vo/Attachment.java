package com.kh.common.model.vo;

import java.util.Date;

public class Attachment {
	
	private int fileNo; // 파일넘버 
	private int refAno; //(1: 회원, 2: 게시판,3: 댓글, 4: 상품, 5: 나눔, 6: 문의, 7:고객센터, 8: 채팅, 9: 배너)	
	private int refBno; // 참조글번호
	private String originName; // 파일원본명
	private String changeName; // 파일수정명
	private String filePath; // 저장폴더 경로
	private Date updateDate; // 업로드 일
	private int fileLevel; //파일레벨 (썸네일 설정시 사용 - 1: 썸네일, 2: 일반)
	public Attachment() {
		super();
	}
	public Attachment(int fileNo, int refAno, int refBno, String originName, String changeName, String filePath,
			Date updateDate, int fileLevel) {
		super();
		this.fileNo = fileNo;
		this.refAno = refAno;
		this.refBno = refBno;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.updateDate = updateDate;
		this.fileLevel = fileLevel;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getRefAno() {
		return refAno;
	}
	public void setRefAno(int refAno) {
		this.refAno = refAno;
	}
	public int getRefBno() {
		return refBno;
	}
	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getFileLevel() {
		return fileLevel;
	}
	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}
	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", refAno=" + refAno + ", refBno=" + refBno + ", originName="
				+ originName + ", changeName=" + changeName + ", filePath=" + filePath + ", updateDate=" + updateDate
				+ ", fileLevel=" + fileLevel + "]";
	}
}
