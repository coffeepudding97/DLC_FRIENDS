<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify</title>
<link rel="stylesheet" href="../resources/style/profile.css">
</head>
<body>
	<div id="root">
		<form method="POST" action="">
			<header></header>

			<section id="main-section">
				<!-- 상단 프로필 부분 -->
				<div id="top">
					<!-- 왼쪽 프로필 -->
					<div>
						<!-- 이미지 -->
						<img src="../resources/images/user.png" alt="프로필사진" width="30px"
						height="30px">
						<!-- 유저 이름 -->
						<input type="text" id="name" name="name" placeholder="이름입력"
							autofocus>
					</div>

					<!-- 중앙 메세지 -->
					<div id="profile_center">
						<!-- 태그 -->
						<div></div>

						<!-- 소개글 -->
						<div></div>

						<!-- 유저평가(신고) 태그 -->
						<div></div>

					</div>

				</div>

				<!-- 하단 -->
				<div id="bottom">
					<!-- 왼쪽 수정부분 -->
					<div id="left_modify">
						<!-- 아이디 -->
						<!-- 기존 비밀번호 -->
						<!-- 새로운 비밀번호 -->
						<!-- 생년월일 -->
					</div>
					
					<!-- 오른쪽 완료버튼 -->
					<div id="right_btn">
						<button>수정 완료</button>
					</div>
				</div>
			</section>

			<footer></footer>

		</form>
	</div>
</body>
</html>