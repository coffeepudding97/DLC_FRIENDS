<%@page import="model.post.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.profile.Profile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="../resources/style/profile.css">
</head>
<body>
	<jsp:include page="/header"></jsp:include>
	<div id ="wrap">
	<div id="root">
		<section id="main-section">
			<!-- 상단 프로필 부분 -->
			<div id="top">
				<!-- 왼쪽 프로필 -->
				<div id="profile_left">
					<!-- 이미지 -->
					<c:choose>
						<c:when test="${empty profile.profileImg}">
							<div>
								<img src="../resources/images/user.png" alt="이미지" width="100" height="100" />
							</div>
						</c:when>
						<c:otherwise>
							<div class="profile_contents">
							   <img src="data:image/png;base64, ${profile.profileImg}" alt="이미지" width="100" height="100" />

					<!-- 유저 이름 -->
							</div>
						</c:otherwise>
					</c:choose>
					

				</div>

				<!-- 중앙 메세지 -->
				<div id="profile_center">
					<div class="profile_nickname">
					<p>${profile.nickname }</p>
					</div>
					<div class="profile_id">
						<p>${profile.id }</p>
					</div>

					<!-- 소개글 -->
					<div class="profile_comment">${profile.info }</div>

				</div>
				

				<!-- 오른쪽 버튼 -->
				<div id="profile_right">
					<div class="tag">
					<!-- ^ 태그 -->
					<div class="game_tag">
						<c:forEach items="${selectGameList}" var="selGames">
							<p>#${selGames.gametitle} </p>
						</c:forEach>
					</div>

					<!-- ^ 유저평가(신고) 태그 -->
						<div class="report_tag">
						    <c:forEach items="${rateList}" var="rateTag">
						        <c:set var="curseDisplayed" value="${curseDisplayed or rateTag.curse == 1}" />
						        <c:set var="runDisplayed" value="${runDisplayed or rateTag.run == 1}" />
						        <c:set var="lateDisplayed" value="${lateDisplayed or rateTag.late == 1}" />
						        <c:set var="disturbDisplayed" value="${disturbDisplayed or rateTag.disturb == 1}" />
						        <c:set var="hackDisplayed" value="${ hackDisplayed or rateTag.hack == 1}" />
						    </c:forEach>
						
						    <c:if test="${curseDisplayed}"><p>#욕설</p></c:if>
						    <c:if test="${runDisplayed}"><p>#탈주</p></c:if>
						    <c:if test="${lateDisplayed}"><p>#지각</p></c:if>
						    <c:if test="${disturbDisplayed}"><p>#게임진행방해</p></c:if>
						    <c:if test="${hackDisplayed}"><p>#불법프로그램사용</p></c:if>
						</div>
					</div>
					<div class="profile_user">
					<c:if test="${sessionScope.log == profile.id}">
						<div class="profile_update">
	  						<a href="memberInfoModify">내 정보 수정</a>
						</div>
						<div class="delete_user">
							<a href="deleteUser">회원 탈퇴</a>
						</div>
					</c:if>
					</div>
				</div>

			</div>

			<!-- 하단 -->
			<div id="bottom">
				<!-- 왼쪽 -->
				<div id="bottom_left">
					<!-- 왼쪽 상단 '게시글' -->
						<div class="bottom_write_wrap">
							<div class="bottom_write">
								<div>
									<h1>* 작성한 게시글</h1>
								</div>
								<div>
									<button id="posts_more" onclick="postsMore()">더보기</button>
								</div>
								<div id="now_page">1</div>
								<div class="posts">
									<c:forEach items="${postList}" var="post">
										<ul>
											<li>${post.gameTitle}| ${post.title}</li>
										</ul>
									</c:forEach>
								</div>
							</div>
						</div>

						<!-- 왼쪽 하단 '댓글' -->
					<div class="bottom_reply_wrap">
					<div class="bottom_reply">
							<h1>
								* 작성한 댓글
								</h1>
								<div >
									<c:forEach items="${commentList}" var="comment">
										<div>
											<div>${comment.content} ->${comment.createdTime}
											</div>
										</div>
									</c:forEach>
								</div>
								<button class="comments_title"></button>
					</div>
					</div>
				</div>
				<!-- 오른쪽 -->
				<div id="bottom_right">
					<!-- 오른쪽 상단, 최근파티원 -->
					<div>
							<p>* 최근 파티원</p>
							<ul>
								<c:forEach items="${partyMemList }" var="partyMem">
									<li>${partyMem.userId}</li>
								</c:forEach>
							</ul>
						</div>
				</div>

			</div>
		</section>
	</div>
		</div>
		<jsp:include page="/footer"></jsp:include>
	<script src="../resources/script/profile.js"></script>
</body>
</html>