<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글삭제</title>
</head>
<body>
<c:if test="${result == 1}">
	<script>
		alert("글 삭제 성공");
		location.href = "boardlist.do?page=${page}";
	</script>
</c:if>
<c:if test="${result != 1}">
	<script>
		alert("글 삭제 실패");
		history.back();
	</script>
</c:if>

</body>
</html>