<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입폼</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/menu.jsp"/>
<h2>가입양식</h2>
<form method="post" action="/${initParam.rootPath }/controller" >
<input type="hidden" name="command" value="joinMember">
<c:if test="${requestScope.error_message!=null }">
	<font size="2" color="red">${requestScope.error_message }</font>
</c:if>
<!--
	 등록정보 검증 실패해서 다시 오는 경우 입력 했던 값을 다시 보여주기 위해 입력 태그들에 value를 설정
	 required="required" 는 나중에 추가. (검증 서블릿 처리 보여주고나서)
 -->
	<table width='500'>
		<tr>
			<td>ID</td>
			<td>
				<input type="text" name="id" value="${param.id }" id="id" required="required" size="25"> 
			</td>
		</tr>
		<tr>
			<td>Password</td>
			<td>
				<input type="password" name="password" id="password"  required="required"  size="25" maxlength="10">
			</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>
				<input type="text" name="name" id="name" value="${param.name }" required="required" size="25">
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>
				<input type="email" name="email" id="email" value="${param.email }"  required="required" size="25">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="가입">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>
</body>
</html>