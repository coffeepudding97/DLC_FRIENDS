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
<c:import url="header"></c:import>
<body>
	<script src="../resources/script/post_api.js"></script>
	<div id="root">
		<section id="main-section">
			<div id=div-postinfo>
				<!--
				pageScope. 
				requestScope. 
				sessionScope. 
				applicationScope. 
				-->
				<h1>post_no : ${param.post_no }</h1>
				
				<span><strong>${post.title}</strong></span>
				<span>time : ${requestScope.post.createdTime }</span>
				<span id="viewCount">조회수 : ${requestScope.post.viewCount+1 }</span>
				<hr/>
				<span>${post.userId }</span>
				<form method="post" action="/UpdatePostAction">
					<input type="hidden" name="postNo" value="${requestScope.post.postNo }">
					<input type="submit" value="수정">
				</form>
				<%--<button onclick="">수정</button>--%>
				<button onclick="delPost()">삭제</button>
				<br/>
				<span>${post.gameTitle }</span>
				<span>1</span><span>/</span><span>${post.recruitMax }</span>
				<span id="meetTime">${post.meetTime }</span>
				<span>${post.leaveTime }</span>
				<span id="isEnd"></span>
				<br/>
				<p>${post.content }</p>
			</div>
			<div id=div-join>
				<h1>참가자</h1>
				<ul id=join>
					<c:forEach var="i" begin="0" end="${post.recruitMax-1 }">
						<c:choose>
							<c:when test="${i < requestScope.party.userIds.size() }">
								<li><button onclick="profileClick(this)">${requestScope.profileDtos.get(i).imageHtml }<span class="memberId">${requestScope.profileDtos.get(i).id }</span></button></li>
							</c:when>
							<c:otherwise>
								<li><button onclick="blankClick(this)"><span class="memberId">+</span></button></li>
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
									<p><strong name="userId" style="color:orange;">${cmt.userId }</strong></p>
									<p>${cmt.content }</p>
									<p>${cmt.createdTime }</p>
							</c:when>
							<c:otherwise>
								<li>
									<p><strong name="userId" style="color:blue;">${cmt.userId }</strong></p>
									<p>${cmt.content }</p>
									<p>${cmt.createdTime }</p>
							</c:otherwise>
						</c:choose>
						<form>
								<%-- <form method="post" action="/deleteCmt"> --%>
							<input type="hidden" class="cmtNo" name="cmtNo" value="${cmt.cmtNo }">
							<input type="button" value="댓글" onclick="setRpNo(this)">
						<c:choose>
							<c:when test="${requestScope.post.userId == cmt.userId }">
								<%-- <input type="submit" value="삭제"> --%>
								<input type="button" value="삭제" onclick="delete_comment(this)">
							</c:when>
						</c:choose>
							</form>
						</li>
					</c:forEach>
				</ul>
				<%--아래 post.userID 나중에 로그인한 유저 id로 바꾸기 --%>
				<span>${requestScope.post.userId }</span>
				<form>
					<input type="hidden" id="postNo" name="postNo" value="${param.post_no }">
					<input type="hidden" id="userId" name="userId" value="${requestScope.post.userId }">
					<input type="hidden" id="rpNo" name="rpNo" value="0">
					<span id="replyName"></span>
					<input type="text" id="comment" name="comment">
					<input type="button" value="작성" onclick="post_comment()">
				</form>
			</div>
		</section>
	</div>
</body>
</html>