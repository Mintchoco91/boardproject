package com.project.boardproject.bc.member;

import java.net.InetAddress;

import lombok.Data;

@Data
public class BlockMemberVO {
	private String userId;
	private String pw;
	private String ip;
	private String publicKey;
	
	public BlockMemberVO(String userId, String pw) {
		this.userId = userId;
		this.pw=pw;
	}
	
	public BlockMemberVO(String userId, String pw, String publicKey) {
		this.userId= userId;
		this.pw = pw;
		try {
			InetAddress ipAddress = InetAddress.getLocalHost();
			this.ip=ipAddress.getHostAddress();
			System.out.println("여기서 알려주는 ip: "+ip);
		}catch(Exception e) {
			System.out.println("실패위치: ip호출");
		}
		this.publicKey=publicKey;
		System.out.println(publicKey.length());
	}
}
