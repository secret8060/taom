package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.vo.Member;
import common.controller.AttributeAndView;
import common.controller.Controller;

public class JoinSuccessController implements Controller {

	@Override
	/**
	 * 가입 후 새로고침시 다시 등록되는 것을 방지 하기 위한 Controller
	 * 등록 된 회원의 ID를 요청파라미터로 받아 회원 정보 조회후 join_success.jsp로 이동
	 */
	public AttributeAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		MemberService ms = MemberService.getInstance();
		Member member = ms.getMemberById(id);
		return new AttributeAndView("/WEB-INF/view/member/join_success.jsp", false, "member", member);
	}

}
