package com.project.boardproject.bc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project.boardproject.bc.util.BlockChainUtils;
import com.project.boardproject.bc.util.Configuration;

import lombok.Data;

@Data
@Component
public class Block {
	private long timestamp;
	private String preHash;
	private String hash;
	private long nonce;
	private int difficulty;
	private List<Transaction> transactions;
	
	public static boolean minable;

	public Block(long timestamp, String preHash, String hash, long nonce, int difficulty,
			List<Transaction> transactions) {
		this.timestamp = timestamp;
		this.preHash = preHash;
		this.hash = hash;
		this.nonce = nonce;
		this.difficulty = difficulty;
		this.transactions = transactions;
	}
	
	public Block() { }
	
	public static Block genesis() {
		long timestamp= new Date().getTime();
		String preHash="";
		int difficulty = Configuration.DIFFICULTY;
		int nonce=0;
		ArrayList<Transaction> transactions = new ArrayList<>();
		String hash=calculateHash(timestamp,preHash,difficulty,nonce);
		while(hash.length()<difficulty || !hash.substring(0, difficulty).equals(new String(new char[difficulty]).replace('\0', '0'))) {
			timestamp=new Date().getTime();
			hash=Block.calculateHash(timestamp, preHash, difficulty, nonce);
			nonce++;
		}
		
		return new Block(timestamp,preHash,hash,nonce,difficulty,transactions);
	}

	public static Block mineBlock(Block lastBlock, List<Transaction> transactions) {
		long timeStamp=new Date().getTime();
		long nonce= 0;
		String preHash = lastBlock.getHash();
		int difficulty = lastBlock.getDifficulty();
		String hash="";
		while(hash.length()<difficulty || !hash.substring(0, difficulty).equals(new String(new char[difficulty]).replace('\0', '0'))) {
			timeStamp=new Date().getTime();
			difficulty = Block.adjustDifficulty(lastBlock, timeStamp);
			hash=Block.calculateHash(timeStamp, preHash, difficulty, nonce);
			nonce++;
			if(!minable) return null;
		}
		return new Block(timeStamp, preHash, hash, nonce-1, difficulty, transactions);
	}
	
	public static String calculateHash(long timestamp, String preHash, int difficulty, long nonce) {
		return BlockChainUtils.getSHA256Hash(Long.toString(timestamp)+preHash+
				Integer.toString(difficulty)+Long.toString(nonce));
	}	
	
	public static int adjustDifficulty(Block lastBlock, long currentTime) {
		int difficulty = lastBlock.getDifficulty();
		difficulty = lastBlock.getTimestamp() + Configuration.MINE_RATE > currentTime ? ++difficulty : --difficulty;
		return difficulty;
	}
}
