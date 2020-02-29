<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath }/">SupportDev</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${pageContext.request.contextPath }/">Home</a></li>
				<li><a href="${pageContext.request.contextPath }/board/list">Board</a></li>
				<li><a href="#contact">Contact</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Dropdown
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Nav header</li>
						<li><a href="#">Separated link</a></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav pull-right">
				<c:catch>
					<c:choose>
						<c:when test="${empty authInfo}">
							<li><a href="${pageContext.request.contextPath }/login">Sign
									in</a></li>
							<li><a
								href="${pageContext.request.contextPath }/signup/step1">Sign
									up</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath }/mypage">${authInfo.name}</a></li>
							<li><a href="${pageContext.request.contextPath }/logout">Sign
									out</a></li>
						</c:otherwise>
					</c:choose>
				</c:catch>
			</ul>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>