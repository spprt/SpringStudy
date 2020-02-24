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
	<section id="container">
		<form role="form" method="post" action="${pageContext.request.contextPath }/board/save" enctype="multipart/form-data">
		
		<div class="form-group">
			<label for="subject" class="col-sm-2 control-label">제목</label>
			<input type="text" id="subject" name="subject" class="form-control" value="${read.subject}"/>
		</div>
		<div class="form-group">
			<label for="writer" class="col-sm-2 control-label">작성자</label>
			<input type="text" id="writer" name="writer" class="form-control" value="${authInfo.name}"/>
		</div>
		<div class="form-group">
			<label for="content" class="col-sm-2 control-label">내용</label>
			<textarea id="content" name="content" class="form-control" ><c:out value="${read.content}" /></textarea>
		</div>
		<div>
			<input type="file" name="file">	
		</div>
		<div>
			<button type="submit" class="btn">작성</button>
			<a href="${pageContext.request.contextPath }/board/list"><button type="button" class="list_btn btn btn-primary">목록</button></a>	
		</div>
		</form>
	</section>
	</div>
</body>
</html>