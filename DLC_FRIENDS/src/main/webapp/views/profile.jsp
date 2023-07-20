<%@page import="model.post.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.profile.Profile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta property="og:title" content="DLC_FRIENDS">
<meta property="og:description" content="게임 친구 매칭 웹 플랫폼 DLC 프렌즈">
<meta property="og:image" content="../resources/images/meta_img.jpg">
<title>마이프로필</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="../resources/style/profile.css">
<link rel="shortcut icon" href="../resources/images/favicon.ico">
</head>
<body>
	<jsp:include page="/header"></jsp:include>
	<div id="wrap">
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
									<img src="../resources/images/user.png" alt="이미지" width="100"
										height="100" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="profile_contents">
									<img src="data:image/png;base64, ${profile.profileImg}"
										alt="이미지" width="100" height="100" />

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
						<div>
							<p id="profile_id">${profile.id }</p>
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
									<p class="sel_gametitle" onclick="tagSearch(this)">#${selGames.gametitle}</p>
								</c:forEach>
							</div>

							<!-- ^ 유저평가(신고) 태그 -->
							<div class="report_tag">
								<c:forEach items="${rateList}" var="rateTag">
									<c:set var="curseDisplayed"
										value="${curseDisplayed or rateTag.curse == 1}" />
									<c:set var="runDisplayed"
										value="${runDisplayed or rateTag.run == 1}" />
									<c:set var="lateDisplayed"
										value="${lateDisplayed or rateTag.late == 1}" />
									<c:set var="disturbDisplayed"
										value="${disturbDisplayed or rateTag.disturb == 1}" />
									<c:set var="hackDisplayed"
										value="${ hackDisplayed or rateTag.hack == 1}" />
								</c:forEach>

								<c:if test="${curseDisplayed}">
									<p>#욕설</p>
								</c:if>
								<c:if test="${runDisplayed}">
									<p>#탈주</p>
								</c:if>
								<c:if test="${lateDisplayed}">
									<p>#지각</p>
								</c:if>
								<c:if test="${disturbDisplayed}">
									<p>#게임진행방해</p>
								</c:if>
								<c:if test="${hackDisplayed}">
									<p>#불법프로그램사용</p>
								</c:if>
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


								<div class="posts"></div>
								<div class="post_list">
									<button id="post_minus_btn" onclick="post_minus_btn()">
										<img src="../resources/images/arrow_left.png">
									</button>
									<div id="post_page">1</div>
									<button id="post_plus_btn" onclick="post_plus_btn()">
										<img src="../resources/images/arrow_right.png">
									</button>
								</div>
							</div>
						</div>

						<!-- 왼쪽 하단 '댓글' -->
						<div class="bottom_reply_wrap">
							<div class="bottom_reply">
								<div>
									<h1>* 작성한 댓글</h1>
								</div>
								<div class="comments"></div>
								<div class="reply_list">
									<button id="comment_minus_btn" onclick="comment_minus_btn()">
										<img src="../resources/images/arrow_left.png">
									</button>
									<div id="comment_page">1</div>
									<button id="comment_plus_btn" onclick="comment_plus_btn()">
										<img src="../resources/images/arrow_right.png">
									</button>
								</div>
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