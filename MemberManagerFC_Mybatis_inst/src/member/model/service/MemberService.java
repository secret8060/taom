package member.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.exception.DuplicatedIdException;
import member.model.dao.MemberDAO;
import member.vo.Member;


/**
 * 회원 관리 Business Logic을 처리하는 Business Service 클래스
 * @author kgmyh
 *
 */
public class MemberService {
	private static MemberService instance = new MemberService();
	
	private MemberDAO dao;
	private MemberService(){
		dao = MemberDAO.getInstance();
	}
	public static MemberService getInstance(){
		return instance;
	}
	/**
	 * 가입 처리 메소드
	 * @param member 가입 정보
	 * @throws SQLException
	 * @throws DuplicatedIdException 중복된 아이디일 경우 발생
	 */
	public void joinMember(Member member) throws SQLException,
																			DuplicatedIdException{
		
		//1. 등록된 ID가 있는 지 체크
		//    - 이미 등록된 ID라면 DuplicatedIdException를 발생 시킨다.
		if(dao.selectMemberById(member.getId())!=null){
			throw new DuplicatedIdException(member.getId()+"는 이미 등록된 아이디입니다. ID를 변경하세요");
		}
		//2. 디비에 회원정보 등록 처리
		dao.insertMember(member);
		
		//3. connection close
		
	}
	/**
	 * 전체 회원 목록 조회 처리
	 * @return ArrayList
	 * @throws SQLException
	 */
	public List<Member> getMemberList() throws SQLException{
		
		List <Member> list = dao.selectAllMember();
		
		return list;
	}
	/**
	 * ID로 회원 조회 메소드
	 * @param id : 조회할 회원 ID
	 * @return
	 * @throws SQLException
	 */
	public Member getMemberById(String id) throws SQLException{
		
		Member member = dao.selectMemberById(id);
		
		return member;
	}
	/**
	 * 회원 정보 수정 처리 메소드
	 * @param member 수정할 회원 전보
	 * @throws SQLException
	 */
	public void modifyMember(Member member) throws SQLException{
		
		dao.updateMember(member);
		
	}
	/**
	 * 회원 탈퇴 메소드
	 * @param id 탈퇴할 회원 ID
	 * @throws SQLException
	 */
	public void removeMemberById(String id) throws SQLException {
		
		dao.deleteMemberById(id);
		
	}
	
/*	public int getMemberListPaging(int pageNo) throws SQLException{
//		dao.
	}*/
	
}

















