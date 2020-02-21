<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
    <title>board write | supportDev</title>
</head>
<body>
	<div class="continer">
	BOARD VIEW PAGE
	<a href="${pageContext.request.contextPath }/board/list">목록</a>
	<a href="${pageContext.request.contextPath }/board/edit?id=${read.id}">수정</a>
	<a href="${pageContext.request.contextPath }/board/delete?id=${read.id}">삭제</a>
	<section id="container">
		<table>
			<tbody>
				<tr>
					<td>
						<label for="subject">제목</label>${read.subject}
					</td>
				</tr>	
				<tr>
					<td>
						<label for="writer">작성자</label>${read.writer}
					</td>
				<tr>
				<tr>
					<td>
						<label for="content">내용</label>${read.content}</textarea>
					</td>
				</tr>
			</tbody>			
		</table>
	</section>
	</div>
</body>
</html>