package com.project.boardproject.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoggerInterceptor extends HandlerInterceptorAdapter{
 protected Log log = LogFactory.getLog(LoggerInterceptor.class);
 
 @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
   throws Exception {
  if (log.isDebugEnabled()) {
            log.debug("======================================          START         ======================================");
            log.debug(" Request URI \t:  " + request.getRequestURI());
        }
  
  //모든 URL에 대해 IP 저장 부분 구현할것.
  /*
   * 
   * CREATE TABLE visit_info(
	idx 		int 			NOT NULL AUTO_INCREMENT,
    ip             varchar(20) NOT NULL,
    url             TEXT NOT NULL,
	rgtId		varchar(20) NOT NULL,
	rgtDtm		varchar(15) NOT NULL,
	updId		varchar(20) NULL,
	updDtm		varchar(15) NULL,
     PRIMARY KEY (idx)
);
   * 
   */
  
  return super.preHandle(request, response, handler);
 }
 
 @Override
 public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
   ModelAndView modelAndView) throws Exception {
   if (log.isDebugEnabled()) {
             log.debug("======================================           END          ======================================\n");
      }
 }

}