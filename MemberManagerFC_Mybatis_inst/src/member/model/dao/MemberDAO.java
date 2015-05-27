package member.model.dao;

import java.sql.SQLException;
import java.util.List;

import member.vo.Member;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import common.util.DatabaseUtil;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private SqlSessionFactory factory;
	private String namespace = "member.dao.memberMapper.";
	private MemberDAO(){
		factory = DatabaseUtil.getInstance().getSqlSessionFactory();
	}
	public static MemberDAO getInstance(){
		return instance;
	}
	/**
	 * id로 회원 정보를 조회하여 return하는 메소드
	 * @param id 조회할 회원 ID
	 * @return 
	 * @throws SQLException
	 */
	public Member selectMemberById(String id){
		SqlSession session = factory.openSession();
		try{
			return session.selectOne(namespace+"selectMemberById", id);
		}finally{
			session.close();
		}
	}
	/**
	 * 회원 정보를 DB에 insert하는 메소드
	 * @param member 등록할 회원 정보
	 * @throws SQLException
	 */
	public int insertMember(Member member){
		SqlSession session = factory.openSession(true);
		try{
			return session.insert(namespace+"insertMember", member);
		}finally{
			session.close();
		}
	}
	/**
	 * 회원 정보 수정 메소드
	 * 매개변수로 받은 Member객체의 id 회원의 나머지 정보를 변경한다. 단 ID와 가입일자는 변경 안한다.
	 * @param member
	 * @throws SQLException
	 */
	public int updateMember(Member member){
		SqlSession session = factory.openSession(true);
		try{
			return  session.insert(namespace+"updateMember", member);
		}finally{
			session.close();
		}
	}
	/**
	 * 매개 변수로 받은 ID의 회원을 삭제하는 메소드
	 * @param id
	 * @throws SQLException
	 */
	public int deleteMemberById(String id){
		SqlSession session = factory.openSession(true);
		try{
			return session.insert(namespace+"deleteMemberById", id);
		}finally{
			session.close();
		}
	}
	/**
	 * Member 테이블의 전체 회원정보 조회 처리
	 * @return
	 * @throws SQLException
	 */
	public List<Member> selectAllMember(){
		SqlSession session = factory.openSession();
		try {
			return session.selectList(namespace+"selectAllMember");
		} finally {
			session.close();
		}
	}
	
	public List<Member> selectAllMemberPaging(){
		SqlSession session = factory.openSession();
		try {
			 return session.selectOne(namespace+"selectAllMemberPaging");
		} finally {
			session.close();
		}
	}
	
	public int selectTotalMemberCount(int num){
		SqlSession session = factory.openSession(true);
		try {
			return session.selectOne(namespace+"selectTotalMemberCount", num);
		} finally {
			session.close();
		}
	}
}











