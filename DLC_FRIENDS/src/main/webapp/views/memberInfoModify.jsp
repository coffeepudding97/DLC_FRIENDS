<%@page import="model.profile.Profile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify</title>
</head>
<body>
	<div id="root">
		<jsp:include page="header"></jsp:include>
		<div>
			<h1>프로필 수정</h1>
		</div>

		<section id="main-section">
			<!-- 상단 프로필 부분 -->
			<div id="top">
				<!-- 왼쪽 프로필 -->
				<div>
					<!-- 이미지 -->

					<form action="/uploadImage" method="post"
						enctype="multipart/form-data">
						<input type="file" name="input_image"> <input
							type="submit" value="Upload Image">
					</form>

					<!-- * 유저 이름 -->
					<!-- 
					<div>
						<input type="text" id="nickname" name="nickname"
							placeholder="닉네임 입력">
					</div>
					 -->


				</div>

				<!-- 중앙 메세지 -->
				<div id="profile_center">
					<!-- 태그지정 -->
					<p id="gametitleToggle">* 선호 게임 설정</p>
					<div id="gametitleList">
						<form action="SelectGames" method="POST">
							<div>
								<c:forEach items="${gameList}" var="games">
									<label>${games.gameTitle } <input type="checkbox"
										name="selectGame" value="${games.gameTitle }" />
									</label>
									<br>
								</c:forEach>
							</div>
							<input type="submit" id="game_submit" value="설정완료">
						</form>
					</div>




					<!-- 유저평가(신고) 태그 -->
					<div></div>

				</div>

			</div>

			<!-- 하단 -->
			<form method="POST" action="/memberInfoModify">
				<div id="bottom">
					<!-- 왼쪽 수정부분 -->
					<div id="left_modify">
						<!-- 아이디 -->
						<div>
							<p>* 닉네임</p>
						</div>
						<div>
							<input type="text" id="id" name="id" value="${profile.id }"
								readonly>
						</div>
						<!-- 기존 비밀번호 -->
						<div>
							<p>* 비밀번호 수정</p>
						</div>
						<div>
							<input type="password" name="password" placeholder="기존 비밀번호">
						</div>
						<!-- 새로운 비밀번호 -->
						<div>
							<input type="text" id="newPw" name="newPw" placeholder="새 비밀번호">
						</div>

						<!-- 유저 소개글 -->
						<div>
							<p>* 소개글</p>
						</div>
						<input type="text" id="info" name="info" value="${profile.info }"
							placeholder="소개글을 입력해주세요.">
					</div>

					<!-- 오른쪽 완료버튼 -->
					<div id="right_btn">
						<input type="submit" id="submit-btn" value="회원정보 수정완료">
					</div>
				</div>
			</form>

		</section>

		<footer></footer>

	</div>
	<script src="../resources/script/profile.js"></script>
</body>
</html>