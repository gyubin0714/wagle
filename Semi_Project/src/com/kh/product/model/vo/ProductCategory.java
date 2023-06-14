package com.kh.product.model.vo;

public class ProductCategory {

	private int cateNo;
	private String cateName;
	private int cateParent;

	public ProductCategory() {
		super();
	}
	public ProductCategory(int cateNo, String cateName, int cateParent) {
		super();
		this.cateNo = cateNo;
		this.cateName = cateName;
		this.cateParent = cateParent;
	}

	public int getCateNo() {
		return cateNo;
	}
	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public int getCateParent() {
		return cateParent;
	}
	public void setCateParent(int cateParent) {
		this.cateParent = cateParent;
	}
	@Override
	public String toString() {
		return "ProductCategory [cateNo=" + cateNo + ", cateName=" + cateName + ", cateParent=" + cateParent + "]";
	}
	
}
