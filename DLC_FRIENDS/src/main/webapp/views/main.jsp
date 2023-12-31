<%@page import="java.util.ArrayList"%>
<%@page import="model.main.MainTable"%>
<%@page import="model.main.MainTableDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="wrap">
	<section id="main-section">
		<div class="search_wrap">
		  <div class="search">
		  	<div class="select_area">
		  		<select id="search_select">
		  			<option value="all">전체</option>
		  			<option value="title">제목</option>
		  			<option value="gametitle">게임타이틀</option>
		  			<option value="content">내용</option>
		  		</select>
		  	</div>
		  	<div class="search_area">
		  		<input type="text" id="search">
		  	</div>
		  	<div class="submit_area">
		  		<button onclick="searchPost()"><img src="../resources/images/search.png"></button>
		  	</div>
		  </div>
		</div>
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
		<div class="bottom_buttons">
		<div class="view_more">
			<button id="more_btn" onclick="getMorePosts()"><img src="../resources/images/arrow_down.png"></button>
		</div>
		<div class=write>
			<a id="writing_link" href="/postWrite">글쓰기</a>
		</div>
		</div>
	</section>
	<script src="../resources/script/view-count.js"></script>
</div>