<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
    <title>board write | supportDev</title>
</head>
<body>
    <%@ include file="/WEB-INF/views/includes/03_header.jsp" %>
	<div class="container">
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">제목</label>
			<input type="text" id="title" name="title" class="form-control" value="${read.subject}" readonly="readonly" />
		</div>
		<div class="form-group">
			<label for="writerName" class="col-sm-2 control-label">작성자</label>
			<input type="text" id="writerName" name="writerName" class="form-control" value="${read.writerName}"  readonly="readonly"/>
		</div>
		<div class="form-group">
			<label for="regDate" class="col-sm-2 control-label">작성날짜</label>
			<fmt:formatDate value="${read.regDate}" pattern="yyyy-MM-dd" />	
		</div>
		<div class="form-group">
			<label for="content" class="col-sm-2 control-label">내용</label>
			<textarea id="content" name="content" class="form-control" readonly="readonly"><c:out value="${read.content}" /></textarea>
		</div>
		<div class="form-group">
			<c:forEach var="file" items="${read.files}">
				<a href="${pageContext.request.contextPath}/board/fileDown?id=${file.fileid}">${file.fileName}</a> (${file.fileSize}KB)
			</c:forEach>
		</div>
		<div>
			<c:catch>
				<c:choose>
					<c:when test="${authInfo.id == read.writerId}">
						<a href="${pageContext.request.contextPath }/board/edit?id=${read.id}"><button type="button" class="update_btn btn">수정</button></a>
						<a href="${pageContext.request.contextPath }/board/delete?id=${read.id}"><button type="button" class="delete_btn btn">삭제</button></a>
					</c:when>
				</c:choose>
			</c:catch>
			<a href="${pageContext.request.contextPath }/board/list"><button type="button" class="list_btn btn btn-primary">목록</button></a>	
		</div>
	</div>
</body>
</html>