package com.project.boardproject.bc.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.boardproject.bc.model.Block;
import com.project.boardproject.bc.service.BlockChain;

public class PersistentUtils {
	public static void storeBlockChain(BlockChain blockchain) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(new File(Configuration.Path.filepath), blockchain);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BlockChain readBlockChain(String filePath) {
		BlockChain readChain = null;
		try {
			readChain = new ObjectMapper().readValue(new File(filePath), BlockChain.class);
		} catch(FileNotFoundException e) {
			System.out.println("blockchain.json 파일이 존재하지 않습니다.");
			List<Block> newChain = new LinkedList<>();
			newChain.add(Block.genesis());
			readChain = new BlockChain(newChain);
			System.out.println("newChain(genesis) 생성 완료");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return readChain;
	}
}
