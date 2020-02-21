        <div class="header">
            <nav>
                <ul class="nav nav-pills pull-right">
                	<c:catch>
                	<c:choose>
                	<c:when test="${empty authInfo}">
	                	<li role="presentation"><a href="${pageContext.request.contextPath }/login">Sign in</a></li>
	                    <li role="presentation"><a href="${pageContext.request.contextPath }/signup/step1">Sign up</a></li>
                	</c:when>
                	<c:otherwise>
	                	<li role="presentation"><a href="${pageContext.request.contextPath }/mypage">${authInfo.name}</a></li>
	                    <li role="presentation"><a href="${pageContext.request.contextPath }/logout">Sign out</a></li>
                	</c:otherwise>
                	</c:choose>
                	</c:catch>
                </ul>
            </nav>
            <h3 class="text-muted"><b>Support</b>Dev</h3>
            <li role="presentation"><a href="${pageContext.request.contextPath }/board/list">Board</a></li>
        </div>