package com.kh.notice.model.vo;

public class Category {
	
	private int csCategoryNo;// CS_CATEGORY_NO	NUMBER
	private String csCategoryName;// CS_CATEGORY_NAME	VARCHAR2(30 BYTE)
	public Category() {
		super();
	}
	public Category(int csCategoryNo, String csCategoryName) {
		super();
		this.csCategoryNo = csCategoryNo;
		this.csCategoryName = csCategoryName;
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
	@Override
	public String toString() {
		return "Category [csCategoryNo=" + csCategoryNo + ", csCategoryName=" + csCategoryName + "]";
	}
	
	
	
}
