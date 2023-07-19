<%@page import="model.profile.Profile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify</title>
<link rel="stylesheet" href="../resources/style/memberInfoModify.css">
<link rel="shortcut icon" href="../resources/images/favicon.ico">
</head>
<body>
	<jsp:include page="/header"></jsp:include>
	<c:if test="${ empty sessionScope.log }">
		<c:redirect url="/"></c:redirect>
	</c:if>
	<div id="wrap">
		<div id="root">
			<section id="main-section">
				<div class="main-section-inner">
					<!-- 상단 프로필 부분 -->
					<div id="top">
						<!-- 왼쪽 프로필 -->
						<div class="update_img">
							<div class="update_img_txt">
								<a href="#"><p>프로필 사진 수정</p></a>
							</div>
							<!-- 이미지 -->
							<div class="upload_img">
								<input type="file" id="input_image" name="input_image" accept="image/jpeg, image/png">
								<label class="imgfile" for="input_image"><img src="../resources/images/file_add.png"><h1>파일추가</h1></label>
								<input type="button" id="uploadBtn" value="이미지 업로드">
							</div>


						</div>

						<!-- 중앙 메세지 -->
						<div id="profile_center">
							<!-- 태그지정 -->
							<a href="#"><p id="gametitleToggle">선호 게임 설정</p></a>
							<div id="gametitleList">
								<div class="list_wrap">
									<c:forEach items="${gameList}" var="games">
										<label class="list_content">${games.gameTitle } <input type="checkbox"
											name="selectGame" value="${games.gameTitle }" />
										</label>
										<br>
									</c:forEach>
								</div>
								<div class=favorite_game_button>
								<input type="button" id="game_submit" value="설정완료"
									onclick="selectGames()">
								</div>
							</div>

						</div>

					</div>


					<!-- 하단 -->
					<div id="bottom">
						<!-- 왼쪽 수정부분 -->
						<div id="left_modify">
							<!-- 아이디 -->
							<div class="update_nickname">
								<input type="text" id="id" name="id" value="${profile.id }"
									readonly>
							</div>
							<!-- 기존 비밀번호 -->
							<div class="update_password_title">
								<a href="#">
									<p>개인정보 수정</p>
								</a>
							</div>

							<div class="update_password">
								<h1>비밀번호 수정</h1>
								<div class="update_password_input">
									<input type="password" id="password" name="password"
										placeholder="기존 비밀번호">
								</div>
								
								<!-- 새로운 비밀번호 -->
								<div class="update_password_input">
									<input type="password" id="newPw" name="newPw"
										placeholder="새 비밀번호">
								</div>
								<div class="update_password_input">
									<input type="password" id="newPwChk" name="newPwChk"
										placeholder="새 비밀번호 확인">
								</div>
								
								<h1>소개글 수정</h1>
								
								<input type="text" id="info" name="info"
									value="${profile.info }" placeholder="소개글을 입력해주세요.">
								<!-- 오른쪽 완료버튼 -->
								<div id="right_btn">
									<input type="button" id="submit-btn" value="회원정보 수정완료"
										onclick="modifyPwAndInfo()">
								</div>
							</div>

						</div>

					</div>
				</div>

			</section>

		</div>
	</div>
	<script src="../resources/script/memberInfoModify.js"></script>
	<jsp:include page="/footer"></jsp:include>
</body>
</html>