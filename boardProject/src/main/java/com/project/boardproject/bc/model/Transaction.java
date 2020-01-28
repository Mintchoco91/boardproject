package com.project.boardproject.bc.model;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Map;

import com.project.boardproject.bc.service.BlockChain;
import com.project.boardproject.bc.util.BlockChainUtils;

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
    	this.sender=sender;//1. PublicKey로 받는다. 2.Util에서 DB에 등록된 PublicKey를 가져오게 한다.(decode 필요)
    	this.receiver=receiver;
    	this.amount=amount;
    	this.inputs=inputs;
    }
    
    public static Transaction newTransaction(String userId, PrivateKey senderKey, PublicKey sender, String receiver, float amount) {
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
        // Throw exception if balance is not enough <-위에서 이미 체크한거 아닌가? 어차피 이런 일 없을 듯???
        //if(total < amount) {
        //   throw new RuntimeException("Balance is not enough. Balance is: " + total +
        //            ", Amount is: " + amount);
        //}

        //Generate transaction outputs:
        float balance = total - amount; //get value of inputs then the balance
        Transaction transaction = new Transaction(BlockChainUtils.convertKeytoString(sender), receiver, amount, newInputs);
        TransactionOutput txOp1 = new TransactionOutput( receiver, amount, transaction.getTransactionId()); //send value to recipient
        TransactionOutput txOp2 = new TransactionOutput( userId, balance,transaction.getTransactionId()); //send the 'change' back to sender
        transaction.getOutputs().add(txOp1);
        transaction.getOutputs().add(txOp2);


        //Sign the transaction with signature
        transaction.signTransaction(senderKey);

        //Update sender wallet's UTXOs pool
        //이거는 필요 없을 듯, wallet에 utxos 가 없는데 왜 필요
        //newInputs.forEach(transactionInput -> /*위와 동일한 util*/.getUTXOs(sender).remove(transactionInput.getTransactionOutputId()));

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
}
