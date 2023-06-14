package com.kh.notice.model.vo;

import java.sql.Date;

public class Notice {
	
	private int inquiryNo; //INQUIRY_NO	NUMBER
	private int faqNo;
	private int noticeNo;
	private String title; //TITLE	VARCHAR2(100 BYTE)
	private String Content;//CONTENT	VARCHAR2(2000 BYTE)
	private Date writeDate; //WRITE_DATE	DATE
	private String answerNotice; //ANSWER_NOTICE	VARCHAR2(10 BYTE)
	private String answer;
	private String deleteYN; //DELETE_YN	CHAR(3 BYTE)
	private int csCategoryNo; //CS_CATEGORY_NO	NUMBER
	private String csCategoryName; // category 테이블 조인
	private String writer;//WRITER	NUMBER
	private int count; // COUNT 조회수 - NOTICE
	private int vocNo;
	private String vocCategory;
	private String vocMember;
	public Notice() {
		super();
	}
	public Notice(int inquiryNo, int faqNo, int noticeNo, String title, String content, Date writeDate, String answerNotice,
			String answer, String deleteYN, int csCategoryNo, String csCategoryName, String writer, int count,int vocNo, String vocCategory, String vocMember) {
		super();
		this.inquiryNo = inquiryNo;
		this.faqNo = faqNo;
		this.noticeNo = noticeNo;
		this.title = title;
		this.Content = content;
		this.writeDate = writeDate;
		this.answerNotice = answerNotice;
		this.answer = answer;
		this.deleteYN = deleteYN;
		this.csCategoryNo = csCategoryNo;
		this.csCategoryName = csCategoryName;
		this.writer = writer;
		this.count = count;
		this.vocNo = vocNo;
		this.vocCategory = vocCategory;
		this.vocMember = vocMember;
		
	}
	
	
	
	public int getVocNo() {
		return vocNo;
	}
	public void setVocNo(int vocNo) {
		this.vocNo = vocNo;
	}
	public String getVocMember() {
		return vocMember;
	}
	public void setVocMember(String vocMember) {
		this.vocMember = vocMember;
	}
	public String getVocCategory() {
		return vocCategory;
	}
	public void setVocCategory(String vocCategory) {
		this.vocCategory = vocCategory;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public int getFaqNo() {
		return faqNo;
	}
	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}
	public int getInquiryNo() {
		return inquiryNo;
	}
	public void setInquiryNo(int inquiryNo) {
		this.inquiryNo = inquiryNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public String getAnswerNotice() {
		return answerNotice;
	}
	public void setAnswerNotice(String answerNotice) {
		this.answerNotice = answerNotice;
	}
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	public int getCsCategoryNo() {
		return csCategoryNo;
	}
	public void setCsCategoryNo(int csCategoryNo) {
		this.csCategoryNo = csCategoryNo;
	}
	public String getCsCategoryName() {
		return csCategoryName;
	}
	public void setCsCategoryName(String csCategoryName) {
		this.csCategoryName = csCategoryName;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriter() {
		return writer;
	}
	@Override
	public String toString() {
		return "Notice [inquiryNo=" + inquiryNo + ", faq= " + faqNo + ", noticeNo = " + noticeNo + ", title=" + title + ", Content=" + Content + ", writeDate="
				+ writeDate + ", answerNotice=" + answerNotice+ ", answer=" + answer + ", deleteYN=" + deleteYN + ", csCategoryNo="
				+ csCategoryNo + ", csCategoryName=" + csCategoryName + ", writer=" + writer + ", count=" + count + ", vocNo=" + vocNo + ", vocCategory=" + vocCategory +", vocMember=" + vocCategory +"]";
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	

}
