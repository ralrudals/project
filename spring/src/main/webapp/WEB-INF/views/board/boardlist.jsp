<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>
	<a href="boardform.do">글작성</a> <br>
	글갯수 : ${listcount}
	
	<table align="center" border=1 width=700>
		<caption>게시판 목록</caption>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<c:set var = "num" value="${listcount - (page - 1) * 10}"/>
		<c:forEach var = "b" items="${boardlist}">
			<tr>
				<td>
					${num}
					<c:set var="num" value="${num - 1}"/>
				</td>
				<td>
				<a href = "boardcontent.do?no=${b.no}&page=${page}">
					${b.subject}
				</a>
				</td>
				<td>
					${b.writer}
				</td>
				<td>
					<fmt:formatDate value="${b.register}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${b.readcount}
				</td>
			</tr>
		</c:forEach>
	</table>
	<center>
		<c:if test="${listcount > 0}">
			<a href="boardlist.do?page=1" style = "text-decoration: none"> << </a>
			
			<c:if test="${startPage > 10}">
				<a href="boardlist.do?page=${startPage - 10}"> [이전] </a>
			</c:if>
			
			<c:forEach var = "i" begin = "${startPage}" end = "${endPage}">
				<c:if test="${i == page}">
					[${i}]
				</c:if>
				<c:if test="${i != page}">
					<a href = "boardlist.do?page=${i}"> [${i}]</a>
				</c:if>
			</c:forEach>
			
			<c:if test="${startPage < pageCount}">
				<a href="boardlist.do?page=${startPage + 10}"> [다음] </a>
			</c:if>
			
			<a href="boardlist.do?page=${pageCount}" style = "text-decoration: none"> >> </a>
		</c:if>
	</center>
</body>
</html>