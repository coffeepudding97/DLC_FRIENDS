<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="root">
		<section id="main-section">
			<form method="post" action="">
				<div id="div-title">
					<input type="text" id="title" name="title" placeholder="제목" autofocus>
				</div>
				<div id="div-gametitle">
					<select id="gametitle" name="gametitle">
						<option value="리그오브레전드">리그오브레전드</option>
						<option value="배틀그라운드">배틀그라운드</option>
						<option value="오버워치">오버워치</option>
						<option value="발로란트">발로란트</option>
						<option value="기타">기타</option>
					</select>
					<button id="favor1" value="리그오브레전드">리그오브레전드</button>
					<button id="favor2" value="오버워치">오버워치</button>
					<button id="favor3" value="발로란트">발로란트</button>
				</div>
				<div id="div-meeting">
					<input type="number" id="recruitment-max" name="recruitment-max" min="2" step="1" value="2">
					<input type="datetime-local" id="meettime" name="meettime">
					<input type="datetime-local" id="leavertime" name="leavetime">
				</div>
				<div id="div-content">
					<input type="text" id="content" name="content" placeholder="내용">
				</div>
				
			</form>
			<button onclick="location.href='post.jsp'">작성</button>
		</section>
	</div>
</body>
</html>