<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div id="root">
		<section id="main-section">
			<div id=div-postinfo>
				<!--
				pageScope. 
				requestScope. 
				sessionScope. 
				applicationScope. 
				 -->
				<span><strong>${post.title}</strong></span>
				<span>time : ${requestScope.post.createdTime }</span>
				<hr/>
				<p>${post.userId }</p>
				<span>${post.gameTitle }</span>
				<span>1</span><span>/</span><span>${post.recruitMax }</span>
				<span>${post.meetTime }</span>
				<span>${post.leaveTime }</span>
				<br/>
				<p>${post.content }</p>
			</div>
			<div id=div-join>
				<h1>참가자</h1>
				<ul id=join>
					<c:forEach var="i" begin="0" end="${post.recruitMax-1 }">
						<c:choose>
							<c:when test="${i < requestScope.party.userIds.size() }">
								<li><button><img src="#"><span>${requestScope.party.userIds.get(i) }</span></button></li>
							</c:when>
							<c:otherwise>
								<li><button><span>+</span></button></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
				</ul>
			</div>
			<div id="div-comment">
				<h1>댓글</h1>
				<ul id=ul-comment>
					<c:forEach items="${requestScope.cmtList }" var="cmt">
						<c:choose>
							<c:when test="${cmt.rpNo == 0 }">
								<li>
									<p><strong style="color:orange;">${cmt.userId }</strong></p>
									<p>${cmt.content }</p>
									<p>${cmt.createdTime }</p>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<p><strong style="color:blue;">${cmt.userId }</strong></p>
									<p>${cmt.content }</p>
									<p>${cmt.createdTime }</p>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
				<%--아래 post.userID 나중에 로그인한 유저 id로 바꾸기 --%>
				<span>${requestScope.post.userId }</span>
				<form method="post" action="">
					<input type="text" id="input-comment">
					<input type="submit" value="작성">
				</form>
			</div>
		</section>
	</div>
</body>
</html>