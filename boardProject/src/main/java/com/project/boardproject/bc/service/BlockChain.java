package com.project.boardproject.bc.service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.project.boardproject.bc.model.Block;
import com.project.boardproject.bc.model.Transaction;
import com.project.boardproject.bc.model.TransactionInput;
import com.project.boardproject.bc.model.TransactionOutput;
import com.project.boardproject.bc.util.Configuration;
import com.project.boardproject.bc.util.PersistentUtils;

import lombok.Data;

@Data
@Component
public class BlockChain {
	private List<Block> chain;
	
	public BlockChain() {}
	public BlockChain(List<Block> chain) {
		this.chain=chain;
	}
	
	public static BlockChain newBlockchain() {
		List<Block> chain = new LinkedList<>();
		chain.add(Block.genesis());
		return new BlockChain(chain);
	}
	
	public static BlockChain initBlockChainFromJSON(String path) {
		return PersistentUtils.readBlockChain(path);
	}
	
	public static void storeBlockChain(BlockChain blockchain) {
		PersistentUtils.storeBlockChain(blockchain);
	}
	
	public static Map<String, TransactionOutput> getUTXOs(String senderId) {
		Map<String, TransactionOutput> UTXOs = new HashMap<>();
		Map<String, TransactionOutput> allUTXOs = new HashMap<>();
		BlockChain blockchain = BlockChain.initBlockChainFromJSON(Configuration.Path.filepath);
		List<Block> chain = blockchain.getChain();
		Map<String, TransactionOutput> spentTXOs = new HashMap<>();
		for(Block block : chain) {
			for(Transaction transaction : block.getTransactions()) {
				if(transaction.getInputs()==null) continue;
				for(TransactionInput txInput : transaction.getInputs()) {
					spentTXOs.put(txInput.getTransactionOutputId(), txInput.getUTXO());
				}
			}
		}
		for(Block block : chain) {
			for(Transaction transaction : block.getTransactions()) {
				for(TransactionOutput txOutput : transaction.getOutputs()) {
					if(spentTXOs.containsKey(txOutput.getId())) continue;
					else allUTXOs.put(txOutput.getId(), txOutput);
				}
			}
		}
		for(TransactionOutput txOutput:allUTXOs.values()) {
			if (txOutput.getReceiver().equals(senderId)) {
				UTXOs.put(txOutput.getId(), txOutput);
			}
		}
		
		return UTXOs;
	}
	
	public static float getBalance(Map<String, TransactionOutput> UTXOs) {
		float balance =0;
		for(TransactionOutput txOutput : UTXOs.values()) {
			balance+=txOutput.getAmount();
		}
		return balance;
	}
	
	//~~~~~~~~~~~~boolean?
	public void replaceChain(List<Block> newChain) {
		if(newChain.size()<chain.size()) {
			return;
		}
		else if(!isValidChain(newChain)) {
			return;
		}
		chain = newChain;
		Block.minable = false;
	}
	
	public boolean isValidChain(List<Block> newChain) {
		for(int i=1;i<newChain.size();i++) {
			Block curBlock = newChain.get(i);
			Block preBlock = newChain.get(i-1);
			String curHash=Block.calculateHash(curBlock.getTimestamp(), curBlock.getPreHash(), curBlock.getDifficulty(), curBlock.getNonce());
			if(!curBlock.getPreHash().equals(preBlock.getHash()) || !curBlock.getHash().equals(curHash)) {
				return false;
			}
		}
		return true;
	}
	
	public Block addBlock(ArrayList<Transaction> transactions) {
		Block.minable=true;
		Block block = Block.mineBlock(chain.get(chain.size()-1), transactions);
		if(block != null) {
			chain.add(block);
		}
		return block;
	}
	
}
