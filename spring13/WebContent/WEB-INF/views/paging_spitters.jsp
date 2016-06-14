<%@ page language="java" import="java.lang.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
   <div class="listTitle">
      <h1>current page index: [${spitter_nextpage}], page num is "${spitter_pagenum}"</h1>
      <ul class="spitterList">
        <c:forEach items="${mySpitters}" var="spitter" >
          <li id="spitter_${spitter.id}/>">
          	<c:url value="/spitter/show/${spitter.id}" var="mylink"/>
	    	<c:url value="/spitter/delete?username=${spitter.username}&curpage=${curpage }" var="delete_url" />
        	${spitter.username} <a href="${more_url}">specific info of user[${spitter.username}]</a> <a href="${delete_url}"> 删除  </a>	        	        
          </li>
        </c:forEach>
      </ul>
    </div>
    
    <c:if test="${spitter_nextpage > 1}">
    	<c:url value="/spitter/paging?curpage=${spitter_nextpage-1}" var="mylink"/>
	    <a href="${mylink}">&lt;&lt;page up</a>	
    </c:if>
    
    <c:if test="${spitter_nextpage < spitter_pagenum}">
    	<c:url value="/spitter/paging?curpage=${spitter_nextpage+1}" var="mylink"/>
    	<a href="${mylink}">&nbsp;&nbsp;page down &gt;&gt;</a>
    </c:if>
    
  </body>
</html>
