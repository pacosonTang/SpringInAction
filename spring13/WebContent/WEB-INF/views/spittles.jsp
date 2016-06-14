<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="spittleForm">
      <h1>Spit it out...</h1>
      <form method="POST" name="spittleForm">
        <input type="hidden" name="latitude">
        <input type="hidden" name="longitude">
        <textarea name="message" cols="80" rows="5"></textarea><br/>
        <input type="submit" value="Add" />
      </form>
    </div>
    <div class="listTitle">
      <h1>Recent Spittles</h1>
      <ul class="spittleList">
        <c:forEach items="${spittles}" var="spittle" >
          <li id="spittle_${spittle.id}/>">
        	<c:url value="/spittle/show?spittle_id=${spittle.id}" var="more_url" />        	
        	${spittle.name} <a href="${more_url}"> for spec info </a> 
          </li>
        </c:forEach>
      </ul>
      <c:if test="${fn:length(spittles) gt 1}">
        <hr />
        <c:url value="/spittle/paging?curpage=1" var="more_url" />
        <a href="${more_url}">paging</a>
      </c:if>
    </div>
  </body>
</html>