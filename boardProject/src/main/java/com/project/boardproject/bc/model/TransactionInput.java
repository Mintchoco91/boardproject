package com.project.boardproject.bc.model;

import lombok.Data;

@Data
public class TransactionInput {
    private String transactionOutputId;
    private TransactionOutput UTXO;

    public TransactionInput() {}
    
    public TransactionInput(TransactionOutput UTXO) {
        this.UTXO = UTXO;
        this.transactionOutputId = UTXO.getId();
    }
}
