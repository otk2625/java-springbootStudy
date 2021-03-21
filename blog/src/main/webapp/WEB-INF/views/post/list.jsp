<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file = "../layout/header.jsp" %>


<div class="container">

<c:forEach var = "post" items="${posts.content}">
 <div class="card">
    <div class="card-body">
    <div class = "d-flex justify-content-between">
      <h4 class="card-title">${post.title}</h4>
      <div>작성자 : ${post.user.username}</div>
      </div>
      <a href="/post/${post.id}" class="btn btn-primary">상세보기</a>
    </div>
  </div>
  <br>
</c:forEach>

<input type="hidden" value="${posts.number}" name="page" />

<ul class="pagination">
	<c:choose>
		<c:when test="${mode eq 'search'}">
			<!-- 여기가 검색모드일 때 -->
			<c:choose>
		<c:when test="${posts.first}">
			<li class="page-item disable"><a class="page-link">Previous</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="?searchname=${searchname}&page=${posts.number + -1}">Previous</a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${posts.last}">
		<li class="page-item disable" ><a class="page-link">Next</a></li>
		</c:when>
		<c:otherwise>
		<li class="page-item "><a class="page-link" href="?searchname=${searchname}&page=${posts.number + 1}">Next</a></li>
		</c:otherwise>
	</c:choose>
		</c:when>
		
		
		
		
		<c:otherwise>
			<!-- 여기가 검색모드가 아닐 때 -->
			<c:choose>
		<c:when test="${posts.first}">
			<li class="page-item disable"><a class="page-link">Previous</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="?page=${posts.number + -1}">Previous</a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${posts.last}">
		<li class="page-item disable" ><a class="page-link">Next</a></li>
		</c:when>
		<c:otherwise>
		<li class="page-item "><a class="page-link" href="?page=${posts.number + 1}">Next</a></li>
		</c:otherwise>
	</c:choose>
		</c:otherwise>
	</c:choose>

	



</ul>
  
</div>
<script>

console.log(document.getElementsByName("page")[0].value);
</script>

<%@ include file = "../layout/footer.jsp" %>
