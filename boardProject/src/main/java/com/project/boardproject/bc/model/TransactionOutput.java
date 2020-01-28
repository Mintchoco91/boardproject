package com.project.boardproject.bc.model;

import java.util.Date;

import com.project.boardproject.bc.util.BlockChainUtils;

import lombok.Data;

@Data
public class TransactionOutput {
	private String Id;
    private String receiver;
    private float amount;
    private String parentTransId;
    private long timestamp;
    
    public TransactionOutput() {}
    
    public TransactionOutput(String receiver, float amount, String parentTransId) {
        this.Id = BlockChainUtils.generateTransactionId();
        this.receiver = receiver;
        this.amount = amount;
        this.parentTransId = parentTransId;
        this.timestamp = new Date().getTime();
    }
}
