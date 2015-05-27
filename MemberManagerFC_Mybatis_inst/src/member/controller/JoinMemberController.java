package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.exception.DuplicatedIdException;
import member.model.service.MemberService;
import member.vo.Member;

import common.controller.AttributeAndView;
import common.controller.Controller;

public class JoinMemberController implements Controller {
	
	@Override
	public AttributeAndView handle(HttpServletRequest request, HttpServletResponse response) {
		AttributeAndView av = null;
		//1. 요청파라미터 조회
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email= request.getParameter("email");
		/******************************************
		*  검증 로직
		*******************************************/
		if(id==null||id.trim().length()==0){
			av = new AttributeAndView("/WEB-INF/view/member/join_form.jsp", false, "error_message", "ID는 필수 입력사항입니다.");
			return av;//더이상 진행할 필요 없으므로 return 한다.
		}
		/*
		if(id.length() <6){
			av = new AttributeAndView("/WEB-INF/view/member/join_form.jsp", false, "error_message", "ID는 6글자 이상 입력하세요");
			return av;
		}
		*/
		if(password==null||password.trim().length()==0){
			av = new AttributeAndView("/WEB-INF/view/member/join_form.jsp", false, "error_message", "Password는 필수 입력사항입니다.");
			return av;//더이상 진행할 필요 없으므로 return 한다.
		}
		
		if(name==null||name.trim().length()==0){
			av = new AttributeAndView("/WEB-INF/view/member/join_form.jsp", false, "error_message", "이름은 필수 입력사항입니다.");
			return av;//더이상 진행할 필요 없으므로 return 한다.
		}
		if(email==null||email.trim().length()==0){
			av = new AttributeAndView("/WEB-INF/view/member/join_form.jsp", false, "error_message", "이 메일은 필수 입력사항입니다.");
			return av;//더이상 진행할 필요 없으므로 return 한다.
		}
		
		Member member = new Member(id, password, name, email);
		
		//2. Business Logic 처리 - ms.joinMember()
		MemberService ms = MemberService.getInstance();
		
		try {
			ms.joinMember(member);
			av = new AttributeAndView(request.getContextPath()+"/controller?command=joinSuccess&id="+id, true);
		} catch(DuplicatedIdException e){
			av = new AttributeAndView("/WEB-INF/view/member/join_form.jsp", false,"error_message", id+" - 이미 가입된 ID입니다.");
		} catch(Exception e){
			e.printStackTrace();
			av = new AttributeAndView("/WEB-INF/view/error.jsp", false,"error_message", e.getMessage());
		}
		 
		return av;
	}

}
