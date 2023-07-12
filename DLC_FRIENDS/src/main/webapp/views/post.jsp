<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/style/post.css" />
</head>
<body>
	<c:import url="header"></c:import>
	<script src="../resources/script/post_api.js"></script>
	<div id="wrap">
		<div id="root">
			<section id="main-section">
				<div id="main_post">
					<div id=div-postinfo>
						<!--
				pageScope. 
				requestScope. 
				sessionScope. 
				applicationScope. 
				-->
						<div class="main_title">
							<h2>${param.post_no }</h2>
							<h1>${post.title}</h1>
						</div>
						<div class="main_title_bottom_wrap">
							<span>글쓴이 : ${post.userId }</span>
							<div class="main_title_bottom">
								<span>작성 시간 : ${requestScope.post.createdTime }</span> <span
									id="viewCount">조회수 : ${requestScope.post.viewCount+1 }</span>
							</div>
						</div>
						<hr />

						<%--<button onclick="">수정</button>--%>
						<c:if test="${sessionScope.log eq requestScope.post.userId }">
							<form method="post" action="/UpdatePostAction">
								<input type="hidden" name="postNo"
									value="${requestScope.post.postNo }"> <input
									type="submit" value="수정">
							</form>
							<button onclick="delPost()">삭제</button>
						</c:if>

						<br /> <span>${post.gameTitle }</span> <span>1</span><span>/</span><span>${post.recruitMax }</span>
						<span id="meetTime">${post.meetTime }</span> <span>${post.leaveTime }</span>
						<span id="isEnd"></span> <br />
						<hr />
					</div>
					<div class="post_content">
						<p>${post.content }</p>
					</div>
					<div id=div-join>
						<h1>참가자</h1>
						<div id=join>
							<c:forEach var="i" begin="0" end="${post.recruitMax-1 }">
								<c:choose>
									<c:when test="${i < requestScope.party.userIds.size() }">
										<div class="join_member">
											<button onclick="profileClick(this)">${requestScope.profileDtos.get(i).imageHtml }<span
													class="memberId">${requestScope.profileDtos.get(i).id }</span>
											</button>
										</div>
									</c:when>
									<c:otherwise>
										<div class="button_join">
											<button onclick="blankClick(this)">
												<span class="memberId">+</span>
											</button>
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>

						</div>
					</div>
				</div>
				<div id="div-comment">
					<h1>댓글</h1>
					<ul id=ul-comment>
						<c:forEach items="${requestScope.cmtList }" var="cmt">
							<c:choose>
								<c:when test="${cmt.rpNo == 0 }">
									<li class="cmt">
										<p>
											<strong name="userId" style="color: orange;">${cmt.userId }</strong>
										</p>
										<p>${cmt.content }</p>
										<p>${cmt.createdTime }</p>
								</c:when>
								<c:otherwise>
									<li class="rp">
										<p>
											<strong name="userId" class="reply" style="color: blue;">${cmt.userId }</strong>
										</p>
										<p>${cmt.content }</p>
										<p>${cmt.createdTime }</p>
								</c:otherwise>
							</c:choose>
							<form>
							<div>
								<%-- <form method="post" action="/deleteCmt"> --%>
								<input type="hidden" class="cmtNo" name="cmtNo"
									value="${cmt.cmtNo }"> <input type="button"
									value="답글 쓰기" class="comment_answer" onclick="setRpNo(this)">
								<c:choose>
									<c:when test="${sessionScope.log == cmt.userId }">
										<%-- <input type="submit" value="삭제"> --%>
										<input type="button" value="삭제" onclick="delete_comment(this)">
									</c:when>
								</c:choose>
							</div>
							</form>
							</li>
						</c:forEach>
					</ul>
					<%--아래 post.userID 나중에 로그인한 유저 id로 바꾸기 --%>
					<%--<span>${requestScope.post.userId }</span>--%>
					<div class="writer_id">
					<span>작성자 : ${sessionScope.log }</span>
					<form>
						<input type="hidden" id="postNo" name="postNo"
							value="${param.post_no }"> 
						<input type="hidden" id="userId" name="userId" value="${sessionScope.log }"> 
						<input type="hidden" id="rpNo" name="rpNo" value="0"> 
						<span id="replyName"></span> 
						<div class="writer_txt">
						<input type="text" id="comment" name="comment"> 
						<input type="button" id="commentWrite" value="작성" onclick="post_comment()">
						</div>
					</form>
					</div>
				</div>
			</section>
		</div>
	</div>
	<c:import url="footer"></c:import>
</body>
</html>