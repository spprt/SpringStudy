<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
    <title>board list | supportDev</title>
</head>
<body>
	<div class="continer">
	BOARD LIST PAGE
	<selection id="container">
		<form role="form" method="get">
			<table class="table table-hover">
				<thead>
					<tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일</th></tr>
				</thead>
				<c:forEach items="${list}" var="list">
					<tr>
						<td><c:out value="${list.id}"/></td>
						<td><a href="${pageContext.request.contextPath }/board/view?id=${list.id}"><c:out value="${list.subject}"/></a></td>
						<td><c:out value="${list.writer}"/></td>
						<td><c:out value="${list.regDate}"/></td>
					</tr>
				</c:forEach>
			</table>
			<!-- search area -->
			<!-- page area -->
			<div class="col-md-offset-3">
				<ul class="pagination">
<%-- 				<c:if test="${page.prev }"> --%>
<!-- 				    <li> -->
<%-- 				        <a href='<c:url value="/board/list?page=${page.startPage-1 }"/>'><i class="fa fa-chevron-left"></i></a> --%>
<!-- 				    </li> -->
<%-- 				    </c:if> --%>
<%-- 				    <c:forEach begin="${page.startPage }" end="${page.endPage }" var="pageNum"> --%>
<!-- 				    <li> -->
<%-- 				        <a href='<c:url value="/board/list?page=${pageNum }"/>'><i class="fa">${pageNum }</i></a> --%>
<!-- 				    </li> -->
<%-- 				    </c:forEach> --%>
<%-- 				    <c:if test="${page.next && page.endPage >0 }"> --%>
<!-- 				    <li> -->
<%-- 				        <a href='<c:url value="/board/list?page=${page.endPage+1 }"/>'><i class="fa fa-chevron-right"></i></a> --%>
<!-- 				    </li> -->
<%-- 			    </c:if> --%>
				</ul>
			</div>
		</form>
	<a href="${pageContext.request.contextPath }/board/write">글쓰기</a>
	</div>
</body>
</html>