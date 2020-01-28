package com.project.boardproject.bc.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.project.boardproject.bc.model.Block;
import com.project.boardproject.bc.model.Msg;
import com.project.boardproject.bc.model.Transaction;
import com.project.boardproject.bc.model.TransactionPool;
import com.project.boardproject.bc.service.BlockChain;
import com.project.boardproject.bc.util.Configuration;

import lombok.Data;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

@Data
public class Connection implements Runnable {

    private Socket s;
    private ServerSocket ss;
    private BufferedReader in;
    private BufferedWriter out;

    private TransactionPool pool;
    private BlockChain blockchain;

    public Connection (Socket s, ServerSocket ss, TransactionPool pool, BlockChain blockchain) {
        this.s = s;
        this.ss = ss;
        this.pool = pool;
        this.blockchain = blockchain;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        } catch (IOException e) {
            try {
                s.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Handle incoming message
     * @param s incoming json string
     */
    private void msgHandler(String s) {
        System.out.println(s);
        try {
            ObjectMapper om = new ObjectMapper();
            Msg msg = om.readValue(s, Msg.class);
            if (msg.type.equals(Configuration.MessageType.CHAIN)) {
                TypeFactory tf = om.getTypeFactory();
                List<Block> newChain = om.readValue(msg.body, tf.constructCollectionType(List.class, Block.class));
                for (Block b : newChain) {
                    System.out.println(b);
                }
                blockchain.replaceChain(newChain);
            } else if (msg.type.equals(Configuration.MessageType.TRANSACTION)) {
                pool.updateOrAddTransaction(om.readValue(msg.body, Transaction.class));
            } else if (msg.type.equals(Configuration.MessageType.CLEAR)) {
                TypeFactory tf = om.getTypeFactory();
                List<Transaction> validTransaction = om.readValue(msg.body, tf.constructCollectionType(List.class, Transaction.class));
                pool.updateTransactionPool(validTransaction);
            } else if (msg.type.equals(Configuration.MessageType.REGISTRATION)) {
                P2pServer.Pair(msg.body);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Thread entrance, read the json message from TCP connection
     */
    public synchronized void run() {
        try {
            StringBuilder sb = new StringBuilder();
            String l;
            while (!s.isClosed() && s.isConnected()) {
                while ((l = in.readLine()) != null) {
                    sb.append(l);
                }
                msgHandler(sb.toString());
                //TODO: Do we need any response?
                in.close();
                out.close();
                s.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isClosed() {
        return s.isClosed();
    }
}

