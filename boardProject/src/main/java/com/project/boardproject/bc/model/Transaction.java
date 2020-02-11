package com.project.boardproject.bc.model;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.project.boardproject.bc.blockBoard.BlockBoardVO;
import com.project.boardproject.bc.service.BlockChain;
import com.project.boardproject.bc.service.impl.BlockBoardService;
import com.project.boardproject.bc.util.BlockChainUtils;
import com.project.boardproject.bc.util.Configuration;

import lombok.Data;

@Data
public class Transaction {
	private String transactionId;
    //private PublicKey sender;
	private String sender;
    private String receiver;
    private float amount;
    private byte[] signature;

    private ArrayList<TransactionInput> inputs;
    private ArrayList<TransactionOutput> outputs;

    public Transaction() {}
    public Transaction(String sender, String receiver, float amount, ArrayList<TransactionInput> inputs) {
    	this.transactionId=BlockChainUtils.generateTransactionId();
    	this.sender=sender;
    	this.receiver=receiver;
    	this.amount=amount;
    	this.inputs=inputs;
    	this.outputs=new ArrayList<>();
    }
    
    public static Transaction newTransaction(String userId, PrivateKey senderKey, String sender, String receiver, float amount) {
    	Map<String, TransactionOutput> UTXOs = BlockChain.getUTXOs(userId);
    	if(amount < 0.1f) {
            System.out.println("Transaction Inputs too small: " + amount);
            System.out.println("Please enter the amount greater than " + 0.1f);
            return null;
        } else if(BlockChain.getBalance(UTXOs) < amount) {
            System.out.println("There is not enough balance in the wallet");
            return null;
        }
    	
    	float total = 0;
        ArrayList<TransactionInput> newInputs = new ArrayList<>();
        for(TransactionOutput UTXO : UTXOs.values()) {
            total += UTXO.getAmount();
            TransactionInput newInput = new TransactionInput(UTXO);
            newInputs.add(newInput);
            if(total < amount) {
                continue;
            } else {
                break;
            }
        }
        float balance = total - amount; //get value of inputs then the balance
        Transaction transaction = new Transaction(sender, receiver, amount, newInputs);
        TransactionOutput txOp1 = new TransactionOutput( receiver, amount, transaction.getTransactionId()); //send value to recipient
        TransactionOutput txOp2 = new TransactionOutput( userId, balance,transaction.getTransactionId()); //send the 'change' back to sender
        transaction.getOutputs().add(txOp1);
        transaction.getOutputs().add(txOp2);
        transaction.signTransaction(senderKey);
        
        return transaction;
    }

    public void signTransaction(PrivateKey privateKey) {
        String data = receiver + outputs.toString();
        signature = BlockChainUtils.applySignature(privateKey, data);
    }
    
    public boolean verifyTransaction() {
    	String data=receiver+outputs.toString();
    	return BlockChainUtils.verifySignature(BlockChainUtils.convertStringtoPublicKey(sender), data, signature); 
    			//&& ((this.getInputs() == null) || (this.calculateInputsSum() == this.calculateOutputsSum()));
    }
    
    public static Transaction rewardMinner(PrivateKey miner, PublicKey minerKey, String minerId, float reward) {
    	Transaction rewardTransaction = new Transaction(BlockChainUtils.convertKeytoString(minerKey), minerId, reward, null);
    	TransactionOutput newOutput = new TransactionOutput(minerId, reward, rewardTransaction.getTransactionId());
    	ArrayList<TransactionOutput> newOutputs = new ArrayList<>();
    	newOutputs.add(newOutput);
    	rewardTransaction.setOutputs(newOutputs);
    	rewardTransaction.signTransaction(miner);
    	
    	return rewardTransaction;
    }
    
    public static ArrayList<Transaction> rewardBoard(List<BlockBoardVO> boardList, PrivateKey miner, PublicKey minerKey, List<Block> chain) {
    	ArrayList<Transaction> boardReward = new ArrayList<>();
    	
    	SimpleDateFormat form = new SimpleDateFormat("yyyyMMddHHmmss");
    	Date nowTime=new Date();
		String nowTimeString = form.format(nowTime);
		long nowTimeInt = Long.parseLong(nowTimeString);
    	for(BlockBoardVO vo: boardList) {
    		long voTimeMILI=Long.parseLong(vo.getTimestamp());
    		Date voTimeDate = new Date(voTimeMILI);
    		long voTime=Long.parseLong(form.format(voTimeDate));
    		if(voTime-nowTimeInt < Configuration.BOARD_REWARD_TIME_DIF) break;
    		Transaction newTx = new Transaction(BlockChainUtils.convertKeytoString(minerKey),vo.getRegisterId(),vo.getCoinValue(),null);
        	TransactionOutput newOutput = new TransactionOutput(vo.getRegisterId(), vo.getCoinValue(), newTx.getTransactionId());
        	ArrayList<TransactionOutput> newOutputs = new ArrayList<>();
        	newOutputs.add(newOutput);
        	newTx.setOutputs(newOutputs);
        	newTx.signTransaction(miner);
        	boardReward.add(newTx);
    	}
    	return boardReward;
    }
}
