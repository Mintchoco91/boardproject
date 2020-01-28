package com.project.boardproject.bc.controller;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.boardproject.bc.blockBoard.BlockBoardVO;
import com.project.boardproject.bc.member.BlockMemberVO;
import com.project.boardproject.bc.model.Transaction;
import com.project.boardproject.bc.model.TransactionPool;
import com.project.boardproject.bc.network.P2pServer;
import com.project.boardproject.bc.service.BlockChain;
import com.project.boardproject.bc.service.Miner;
import com.project.boardproject.bc.service.impl.BlockBoardService;
import com.project.boardproject.bc.service.impl.BlockMemberService;
import com.project.boardproject.bc.util.BlockChainUtils;
import com.project.boardproject.bc.util.Configuration;

@Controller
public class BlockChainController {

	@Autowired
    private BlockChain blockChain;
	
    @Autowired
    private P2pServer p2pServer;

    @Autowired
    private Miner miner;

    @Autowired
    private TransactionPool transactionPool;
	
	@Autowired
	private BlockMemberService blockMemberService;
	
	@Autowired
	private BlockBoardService boardService;

	@RequestMapping("blockTest")
	public String blockTest() {
		return "blockchain/blockTest";
	}
	
	@RequestMapping(value = "blockMemberRegister", method = RequestMethod.POST)
	public String blockMemberRegister(String userId, String pw, Model model) {
		PrivateKey privateKey = null;
		PublicKey publicKey = null;
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC","SunEC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp192k1");
			keyGen.initialize(ecSpec, random);
			KeyPair keyPair=keyGen.generateKeyPair();
			privateKey=keyPair.getPrivate();
			publicKey=keyPair.getPublic();
		} catch(Exception e) {
			e.printStackTrace();
		}
		String publicKeyString = BlockChainUtils.convertKeytoString(publicKey);
		String privateKeyString = BlockChainUtils.convertKeytoString(privateKey);
		model.addAttribute("privateKeyString", privateKeyString);
		
		BlockMemberVO blockMemberVO = new BlockMemberVO(userId, pw, publicKeyString);
		int result = blockMemberService.insertBlockMember(blockMemberVO);
		if(result==0) {
			System.out.println("회원가입 성공");
			return "blockchain/blockKey";
		}
		else {
			System.out.println("회원가입 실패");
			return "blockchain/blockMember";
		}
	}
	@RequestMapping("blockSend")
	public String blockSend() {
		return "blockchain/blockSend";
	}
	
	@RequestMapping("sendCoin")
	public String sendCoin(String receiver, float amount, String privateKeyString, HttpSession session) {
		Transaction newTransaction = null;
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] decodedPrivateKey=decoder.decode(privateKeyString);
			//로그인된 아이디와 일치하는 publicKey 가져와야함
			String publicKeyString = blockMemberService.getPublicKey((String)session.getAttribute("userId"));
			byte[] decodedPublicKey=decoder.decode(publicKeyString);
			PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodedPrivateKey);
			PKCS8EncodedKeySpec publicKeySpec = new PKCS8EncodedKeySpec(decodedPublicKey);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
			PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
			newTransaction = Transaction.newTransaction((String)session.getAttribute("userId"), privateKey, publicKey, receiver, amount);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Transaction newTransaction = wallet.createTransaction(recipient, amount, transactionPool);
		//1. make Transaction
		transactionPool.updateOrAddTransaction(newTransaction);
		p2pServer.broadcastTransaction(newTransaction);
		return "blockchain/blockTest";
	}
	
	@RequestMapping("blockBoard")
	public String blockBoard(Model model) {
		List<BlockBoardVO> blockBoardList = new ArrayList<>();
		blockBoardList = boardService.getList();
		model.addAttribute("blockBoardList", blockBoardList);
		return "blockchain/blockBoard";
	}
	
	@RequestMapping("blockBoardRegister")
	public String blockBoardRegister(HttpSession session, Model model) {
		String userId=(String)session.getAttribute("userid");
		if(userId==null) return this.blockBoard(model);
//		BlockBoardVO vo = BlockBoardVO.newBlockBoardVO(userId);
//		if(boardService.insertBlockBoard(vo)==1) 
		if(boardService.insertBlockBoard(userId)==1)
			System.out.println("등록 성공");
		else System.out.println("등록실패");
		return this.blockBoard(model);
	}
	
	@RequestMapping("blockUpVote")
	public String blockUpVote(int idx, Model model) {
		if(boardService.updateUpVote(idx)==1)
			System.out.println("upVote 성공");
		else System.out.println("upVote 실패");
		return this.blockBoard(model);
	}
	
	@RequestMapping("blockDownVote")
	public String blockDownVote(int idx, Model model) {
		if(boardService.updateDownVote(idx)==1)
			System.out.println("downVote 성공");
		else System.out.println("downVote 실패");
		return this.blockBoard(model);
	}
	
	@RequestMapping("blockMember")
	public String blockMember() {
		return "blockchain/blockMember";
	}
	
	@RequestMapping("blockMemberLoginCheck")
	public String blockMemberLoginCheck(String userId, String pw, HttpSession session) {
		int pwCheck = blockMemberService.loginCheck(userId,pw);

		if (pwCheck == 0) {
			return "blockchain/blockMember";
		}
		session.setAttribute("userid", userId);
		System.out.println("로그인 성공");
		
		this.blockChain=BlockChain.initBlockChainFromJSON(Configuration.Path.filepath);
		this.transactionPool = new TransactionPool();
		this.p2pServer = new P2pServer(8888, blockChain, transactionPool);
		String minerKeyString = blockMemberService.getPublicKey(userId);
		PublicKey minerKey= BlockChainUtils.convertStringtoPublicKey(minerKeyString);
		 
		this.miner = new Miner(blockChain, transactionPool, p2pServer, minerKey, userId);
		return "blockchain/blockTest";
	}
	
	@RequestMapping("startMine")
	public String startMine(String privateKeyString) {
		PrivateKey privateKey= BlockChainUtils.convertStringtoPrivateKey(privateKeyString);
		miner.startMine(privateKey);
		return "blockchain/blockTest";
	}
	
	@RequestMapping("stopMine")
	public String stopMine() {
		miner.stopMine();
		return "blockchain/blockTest";
	}
	
}
