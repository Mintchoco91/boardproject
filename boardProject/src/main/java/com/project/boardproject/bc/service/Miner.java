package com.project.boardproject.bc.service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.boardproject.bc.blockBoard.BlockBoardVO;
import com.project.boardproject.bc.model.Block;
import com.project.boardproject.bc.model.Transaction;
import com.project.boardproject.bc.model.TransactionPool;
import com.project.boardproject.bc.network.P2pServer;
import com.project.boardproject.bc.service.impl.BlockBoardService;
import com.project.boardproject.bc.service.impl.BlockMemberService;
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
	
	private BlockBoardService boardService;

	public Miner() {}
	
//	@Autowired
	public Miner(BlockChain blockchain, TransactionPool pool, P2pServer p2p, PublicKey minerPublicKey, String minerId, BlockBoardService service) {
		this.blockchain =blockchain;
		this.pool = pool;
		this.p2p=p2p;
		this.minerPublicKey=minerPublicKey;
		this.minerId=minerId;
		this.boardService=service;
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
			
			List<Block> chain = blockchain.getChain();
			int number= chain.size()-1;
			String lastTime;
	    	SimpleDateFormat form = new SimpleDateFormat("yyyyMMddHHmmss");
	    	if(number==0) lastTime = "0";
	    	else {
	    		Block block = chain.get(number);
		    	long preTime = block.getTimestamp();
		    	Date newPreTime= new Date(preTime);
		    	lastTime = form.format(newPreTime);
	    	}
			List<BlockBoardVO> boardList = new ArrayList<>();
			boardList=boardService.getList(lastTime);
			validTransaction.addAll(Transaction.rewardBoard(boardList, minerPrivateKey, minerPublicKey,chain));
			Block latestBlock =blockchain.addBlock(validTransaction);
			if(latestBlock == null) {
				pool.updateTransactionPool(chain.get(chain.size()-1).getTransactions());
				continue;
			}
			p2p.broadcastChains(blockchain.getChain());
			pool.updateTransactionPool(validTransaction);
			p2p.broadcastClearTransaction(validTransaction);
			BlockChain.storeBlockChain(blockchain);
		}
	}
}
