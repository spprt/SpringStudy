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
	BOARD WRITE PAGE
	<a href="${pageContext.request.contextPath }/board/list">목록</a>
	<section id="container">
		<form role="form" method="post" action="${pageContext.request.contextPath }/board/update">
		<input type="hidden" name="id" value="${read.id}" readonly="readonly"/>
			<table>
				<tbody>
					<tr>
						<td>
							<label for="subject">제목</label><input type="text" id="subject" name="subject" value="${read.subject}"/>
						</td>
					</tr>	
					<tr>
						<td>
							<label for="writer">작성자</label><input type="text" id="writer" name="writer"  value="${read.writer}"/>
						</td>
					<tr>
					<tr>
						<td>
							<label for="content">내용</label><textarea id="content" name="content" >
								<c:out value="${read.content }"/>
							</textarea>
						</td>
					</tr>
						<td>						
							<button type="submit">수정</button>
						</td>
					</tr>			
				</tbody>			
			</table>
		</form>
	</section>
	</div>
</body>
</html>