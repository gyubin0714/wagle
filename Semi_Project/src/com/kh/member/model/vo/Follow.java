package com.kh.member.model.vo;

public class Follow {
	
	private int memNo; //MEM_NO	NUMBER
	private int following; //FOLLOWING	NUMBER
	private String nickname;
    private int fileNo;
	
	public Follow() {
		super();
	}

	public Follow(int memNo, int following) {
		super();
		this.memNo = memNo;
		this.following = following;
	}
	
	

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public Follow(String nickname, int fileNo) {
		super();
		this.nickname = nickname;
		this.fileNo = fileNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getFollowing() {
		return following;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	public Follow(int memNo, int following, String nickname) {
		super();
		this.memNo = memNo;
		this.following = following;
		this.nickname = nickname;
	}
	

	public Follow(int memNo, int following, String nickname, int fileNo) {
		super();
		this.memNo = memNo;
		this.following = following;
		this.nickname = nickname;
		this.fileNo = fileNo;
	}

	@Override
	public String toString() {
		return "Follow [memNo=" + memNo + ", following=" + following + ", nickname=" + nickname + ", fileNo=" + fileNo
				+ "]";
	}

	
	
	

}
