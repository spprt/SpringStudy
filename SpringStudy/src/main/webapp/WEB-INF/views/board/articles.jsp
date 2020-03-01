<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
</head>
<body>
    <%@ include file="/WEB-INF/views/includes/03_header.jsp" %>
	<div class="container">
	${writerName}님의 다른 글 보기
	<div class="">
		<c:catch>
			<c:choose>
				<c:when test="${not empty authInfo}">
					<div>
						<button type="button" class="btn btn-sm" id="btnWriteForm"><a href="${pageContext.request.contextPath }/board/write">글쓰기</a></button>
					</div>
				</c:when>
			</c:choose>
		</c:catch>
		<form role="form" method="get">
			<table class="table table-hover">
				<colgroup>
					<col width="60px"/><col/><col width="100px"/><col width="120px"/>
				</colgroup>
				<thead>
					<tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일</th></tr>
				</thead>
				<c:forEach items="${list}" var="list">
					<tr>
						<td><c:out value="${list.id}"/></td>
						<td><a href="${pageContext.request.contextPath }/board/view?id=${list.id}"><c:out value="${list.subject}"/></a></td>
						<td><c:out value="${list.writerName}"/></td>
						<td><fmt:formatDate value="${list.regDate}" pattern="yyyy.MM.dd"/></td>
					</tr>
				</c:forEach>
			</table>
			<!-- search area -->
			<!-- page area -->
			<div class="col-md-offset-3">
				<ul class="pagination">
				<c:if test="${page.prev }">
				    <li>
				        <a href='<c:url value="/board/list?page=${page.startPage-1 }"/>'><i class="fa fa-chevron-left"></i></a>
				    </li>
				    </c:if>
				    <c:forEach begin="${page.startPage }" end="${page.endPage }" var="pageNum">
				    <li>
				        <a href='<c:url value="/board/list?page=${pageNum }"/>'><i class="fa">${pageNum }</i></a>
				    </li>
				    </c:forEach>
				    <c:if test="${page.next && page.endPage >0 }">
				    <li>
				        <a href='<c:url value="/board/list?page=${page.endPage+1 }"/>'><i class="fa fa-chevron-right"></i></a>
				    </li>
			    </c:if>
				</ul>
			</div>
		</form>
		</div>
	</div>
</body>
</html>