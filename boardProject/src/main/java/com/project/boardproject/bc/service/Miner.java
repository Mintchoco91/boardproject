package com.project.boardproject.bc.service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.boardproject.bc.model.Block;
import com.project.boardproject.bc.model.Transaction;
import com.project.boardproject.bc.model.TransactionPool;
import com.project.boardproject.bc.network.P2pServer;
import com.project.boardproject.bc.util.Configuration;

import lombok.Data;

@Data
@Component
public class Miner implements Runnable {
	private BlockChain blockchain;
	private TransactionPool pool;
	private P2pServer p2p;
	private boolean run;
	private PrivateKey minerPrivateKey;
	private PublicKey minerPublicKey;
	private String minerId;

	public Miner() {}
	
//	@Autowired
	public Miner(BlockChain blockchain, TransactionPool pool, P2pServer p2p, PublicKey minerPublicKey, String minerId) {
		this.blockchain =blockchain;
		this.pool = pool;
		this.p2p=p2p;
		this.minerPublicKey=minerPublicKey;
		this.minerId=minerId;
	}

	public void startMine(PrivateKey minerPrivateKey) {
		if(run) return;
		run=true;
		this.minerPrivateKey=minerPrivateKey;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void stopMine() {
		run=false;
		Block.minable = false;
	}
	
	public void run() {
		while(run) {
			ArrayList<Transaction> validTransaction = pool.getTransactionList(Configuration.TRANSACTION_NUM);
			validTransaction.add(Transaction.rewardMinner(minerPrivateKey, minerPublicKey, minerId, Configuration.MINING_REWARD));
			Block latestBlock =blockchain.addBlock(validTransaction);
			if(latestBlock == null) {
				List<Block> chain = blockchain.getChain();
				pool.updateTransactionPool(chain.get(chain.size()-1).getTransactions());
				continue;
			}
			p2p.broadcastChains(blockchain.getChain());
			pool.updateTransactionPool(validTransaction);
			p2p.broadcastClearTransaction(validTransaction);
			BlockChain.storeBlockChain(blockchain);
			System.out.println("00000000000000000000000000");
		}
	}
}
