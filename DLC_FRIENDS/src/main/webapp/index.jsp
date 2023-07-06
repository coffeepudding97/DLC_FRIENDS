<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/style/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
DBManager.getConnection();
%>
<div id="root">
	<jsp:include page="header.jsp"></jsp:include>
	<div id ="wrap">
	<section id="main-section">
		<div class=main_table_wrap>
			<ul class=main_table>
				<li>
					<ul class="table_title">
						<li>번호</li>
						<li>게임</li>
						<li>제목</li>
						<li>글쓴이</li>
						<li>작성시간</li>
						<li>모집인원</li>
						<li>조회수</li>
					</ul>
				</li>
				<li>
					<ul class="table_content">
						<li>1</li>
						<li>오버워치</li>
						<li>플레1 경쟁전 같이 할분</li>
						<li>커피푸딩</li>
						<li>11:11</li>
						<li>1/5</li>
						<li>18</li>
					</ul>
				</li>
				<li>
					<ul class="table_content">
						<li>2</li>
						<li>오버워치</li>
						<li>플레1 경쟁전 같이 할분</li>
						<li>커피푸딩</li>
						<li>11:11</li>
						<li>1/5</li>
						<li>18</li>
					</ul>
				</li>
				<li>
					<ul class="table_content">
						<li>3</li>
						<li>오버워치</li>
						<li>플레1 경쟁전 같이 할분</li>
						<li>커피푸딩</li>
						<li>11:11</li>
						<li>1/5</li>
						<li>18</li>
					</ul>
				</li>
				<li>
					<ul class="table_content">
						<li>4</li>
						<li>오버워치</li>
						<li>플레1 경쟁전 같이 할분</li>
						<li>커피푸딩</li>
						<li>11:11</li>
						<li>1/5</li>
						<li>18</li>
					</ul>
				</li>
			</ul>
		</div>
		<div class=write>
			<a href="views/postWrite.jsp">글쓰기</a>
		</div>
	</section>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</div>

</body>
</html>