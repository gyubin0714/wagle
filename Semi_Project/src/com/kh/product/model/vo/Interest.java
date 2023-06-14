package com.kh.product.model.vo;

public class Interest {

	private int productNo;
	private int memNo;
	
	public Interest() {
		super();
	}
	public Interest(int productNo, int memNo) {
		super();
		this.productNo = productNo;
		this.memNo = memNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	@Override
	public String toString() {
		return "Interest [productNo=" + productNo + ", memNo=" + memNo + ", count=" + count + "]";
	}
	
	
}
