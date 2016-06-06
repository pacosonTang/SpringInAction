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
      <h1>current page index: [${nextpage}], page num is "${pagenum}"</h1>
      <ul class="spittleList">
        <c:forEach items="${mySpittles}" var="spittle" >
          <li id="spittle_${spittle.id}/>">
          	<c:url value="/show/${spittle.id}" var="mylink"/>
	    	<a href="${mylink}">${spittle.name}</a>	        	        
          </li>
        </c:forEach>
      </ul>
    </div>
    
    <c:if test="${nextpage > 1}">
    	<c:url value="/paging?curpage=${nextpage-1}" var="mylink"/>
	    <a href="${mylink}">&lt;&lt;page up</a>	
    </c:if>
    
    <c:if test="${nextpage < pagenum}">
    	<c:url value="/paging?curpage=${nextpage+1}" var="mylink"/>
    	<a href="${mylink}">&nbsp;&nbsp;page down &gt;&gt;</a>
    </c:if>
    
  </body>
</html>
