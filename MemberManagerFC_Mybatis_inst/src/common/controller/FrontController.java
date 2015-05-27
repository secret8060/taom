package common.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		1. 공통 전 처리 - 한글 encoding 처리
		String command = request.getParameter("command");
		
//		2. Controller 로직 호출
//		2-1. Controller 조회
		Controller ctr = HandlerMapping.getController(command);
		try {
//		2-2클라이언트 요청에 대한 Controller 로직 호출
			AttributeAndView av = ctr.handle(request, response);
//		3 공통 후 처리 - 후처리 : View로 이동
			if(av.isRedirect()){//리다이렉트 방식으로 이동
				response.sendRedirect(av.getUrl());
			}else{//요청 디스패치 방식으로 이동
				//1. av의 Attribute 속성을 이용해서 request scope에 속성을 binding하는 작업
				Map<String, Object> attributes = av.getAttribute();
				if(attributes!=null&&attributes.size()!=0){//attributes에 값이 있으면 
					Set<Entry<String, Object>> entries = attributes.entrySet();
					for(Entry<String, Object> entry : entries){
						String key = entry.getKey();
						Object value = entry.getValue();
						request.setAttribute(key, value);
					}
					
				}
				//2. 요청 디스패치 방식으로 forward() 처리
				RequestDispatcher rdp = request.getRequestDispatcher(av.getUrl());
				rdp.forward(request, response);
			}
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);//container에게 처리를 맞긴다. 
		}
	}

}
