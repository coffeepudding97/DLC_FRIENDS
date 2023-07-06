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
							<c:when test="${i == 0 }">
								<li><button><img src="#"><span>${post.userId }</span></button></li>
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
					<li>
						<p><strong>homin</strong></p>
						<p>인성 수준... 브론즈가 플레 구하네</p>
						<p>2023-07-04 22:32</p>
					</li>
					<li>
						<p><strong>gitjae</strong></p>
						<p>님 왜 시비임?</p>
						<p>2023-07-04 22:38</p>
					</li>
				</ul>
				<span>gitjae</span>
				<input type="text" id="input-comment">
				<button onclick="" id="btn-comment">작성</button>
			</div>
		</section>
	</div>
</body>
</html>