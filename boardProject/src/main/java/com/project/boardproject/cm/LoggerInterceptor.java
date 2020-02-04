package com.project.boardproject.cm;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.boardproject.cm.service.CmService;
import com.project.boardproject.cm.service.VisitInfoVO;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private CmService cmservice;

	protected Log log = LogFactory.getLog(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		InetAddress local;
		String ip = "";
		if (log.isDebugEnabled()) {
			log.debug(
					"======================================          START         ======================================");
			log.debug(" Request URI \t:  " + request.getRequestURI());
		}

		try {
			local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
		} catch (UnknownHostException e1) {
			log.debug(e1.getMessage());
		}

		//모든 URL에 대해 IP log 기록
		VisitInfoVO visitInfoVO = new VisitInfoVO();
		visitInfoVO.setIp(ip);
		visitInfoVO.setRgtId("UnknownId");
		visitInfoVO.setUrl(request.getRequestURI());

		cmservice.visitInfoInsert(visitInfoVO);

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug(
					"======================================           END          ======================================\n");
		}
	}

}