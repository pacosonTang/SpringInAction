<%@ page language="java" import="java.lang.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="listTitle">
      <h1>Recent Spitters List</h1>
      <ul class="spitterList">
        <c:forEach items="${spitters}" var="spitter" >
          <li id="spitter_${spitter.id}/>">
        	<c:url value="/spitter/show?username=${spitter.username}" var="more_url" />
        	<c:url value="/spitter/delete?username=${spitter.username}&curpage=1" var="delete_url" />
        	${spitter.username} <a href="${more_url}">specific info of user[${spitter.username}]</a> <a href="${delete_url}"> 删除  </a>
          </li>
        </c:forEach>
      </ul>
      <c:if test="${fn:length(spitters) gt 1}">
        <hr />
        <c:url value="/spitter/paging?curpage=1" var="more_url" />
        <a href="${more_url}">paging</a>
      </c:if>
    </div>
  </body>
</html>