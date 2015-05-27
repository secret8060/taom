package common.controller;

import member.controller.JoinMemberController;
import member.controller.JoinSuccessController;
import member.controller.LoginController;
import member.controller.LoginMemberInfoController;
import member.controller.LogoutController;
import member.controller.MemberListController;
import member.controller.ModifyFormController;
import member.controller.ModifyMemberController;
import member.controller.RemoveMemberController;


//client가 요청을 처리할 Controller를 찾아 주는 역할을 하는 클래스.
public class HandlerMapping {
	/**
	 * 클라이언트가 요청한 Contoller 객체를 생성해 return 처리하는 메소드
	 * 
	 * @param command Controller를 조회하기 위한 구분자
	 * @return
	 */
	public static Controller getController(String command){
		System.out.println(command);
		Controller ctr = null;
		if(command.equals("link")){
			ctr = new LinkController();
		}//회원 관련 controller
		else if(command.equals("login")){
			ctr = new LoginController();			
		}else if(command.equals("logout")){
			ctr = new LogoutController();
		}else if(command.equals("memberList")){
			ctr = new MemberListController();
		}else if(command.equals("getMemberInfo")){
			ctr = new LoginMemberInfoController();
		}else if(command.equals("modifyForm")){
			ctr = new ModifyFormController();
		}else if(command.equals("modifyMember")){
			ctr = new ModifyMemberController();
		}else if(command.equals("removeMember")){
			ctr = new RemoveMemberController();
		}else if(command.equals("joinMember")){
			ctr = new JoinMemberController();
		}else if(command.equals("joinSuccess")){
			ctr =new  JoinSuccessController();
		}
		return ctr;
	}
}
