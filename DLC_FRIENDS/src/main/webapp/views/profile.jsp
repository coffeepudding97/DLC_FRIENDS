<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
<link rel="stylesheet" href="../resources/style/profile.css">
</head>
<body>
	<div id="root">
		<header></header>

		<section id="main-section">
			<!-- 상단 프로필 부분 -->
			<div id="top">
				<!-- 왼쪽 프로필 -->
				<div id="profile_left">
					<!-- 이미지 --> 
					<img src="../resources/images/user.png" alt="프로필사진" width="30px"
						height="30px">
					<!-- 유저 이름 -->
					<p>User</p>
				</div>

				<!-- 중앙 메세지 -->
				<div id="profile_center">
					<!-- 태그 -->
					<div>#리그오브레전드 #배틀그라운드 #It Takes Two</div>

					<!-- 소개글 -->
					<div>같이 게임해요~</div>

					<!-- 유저평가(신고) 태그 -->
					<div>#욕설 #게임방해</div>

				</div>
				<!-- 오른쪽 버튼 -->
				<div id="profile_right">
					<button>내 정보 수정</button>
				</div>

			</div>
			<br>

			<!-- 하단 -->
			<div id="bottom">
				<!-- 왼쪽 -->
				<div id="bottom_left">
					<!-- 왼쪽 상단 '게시글' -->
					<div>
						<table>
							<thead>* 작성한 게시글
							</thead>
							<tr>
								<td>리그오브레전드 / 듀오 구해요</td>
							</tr>
							<tr>
								<td>배틀그라운드 / 4인팟 구해요</td>
							</tr>
							<tr>
								<td>It Takes Two / 같이 하실 분 구해요</td>
							</tr>
						</table>
					</div>
					<br>

					<!-- 왼쪽 하단 '댓글' -->
					<div>
						<table>
							<thead>* 작성한 댓글
							</thead>
							<tr>
								<td>댓글1</td>
							</tr>
							<tr>
								<td>댓글2</td>
							</tr>
							<tr>
								<td>댓글3</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 오른쪽 -->
				<div id="bottom_right">
					<!-- 오른쪽 상단, 최근파티원 -->
					<div>
						<ul>
							<li>최근 파티원
								<ol>
									<li>유저1</li>
									<li>유저2</li>
									<li>유저3</li>
								</ol>
							</li>
						</ul>
					</div>
				</div>



			</div>
		</section>

		<footer></footer>

	</div>
</body>
</html>