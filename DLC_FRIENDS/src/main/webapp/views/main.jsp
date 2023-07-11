<%@page import="java.util.ArrayList"%>
<%@page import="model.main.MainTable"%>
<%@page import="model.main.MainTableDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="wrap">
	<section id="main-section">
		<div class=main_table_wrap>
			<ul class=main_table>
				<li class="table_title_wrap">
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
				<li id="lines">
					<c:forEach items="${mainList}" var="post">
						<ul class="table_content" onclick="readPost(this)">
							<li name="post_no" value="${post.postNo }">${post.postNo}</li>
							<li>${post.gameTitle}</li>
							<li>${post.title}</li>
							<li>${post.userId}</li>
							<li>${post.createdTime}</li>
							<li>${post.recruitMax}</li>
							<li>${post.viewCount}</li>
						</ul>
					</c:forEach>
				</li>
			</ul>
		</div>
		<div class=write>
			<a href="views/postWrite.jsp">글쓰기</a>
		</div>
	</section>
	<script src="../resources/script/view-count.js"></script>
</div>