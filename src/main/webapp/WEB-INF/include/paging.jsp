<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!-- c:set : 변수라는 '상자'를 하나 만들고 그 안에 '내용물'을 담아두는 것 -->
  <c:set var="startnum" value="${ sto.pagination.startPage }" />
  <c:set var="endnum" value="${ sto.pagination.endPage }" />
  <c:set var="totPageCount" value="${ sto.pagination.totPageCount }" />
  <div id="paging">
    <table>
      <tr>
	   <!-- 처음 과 이전 -->
       <c:if test="${ startnum gt 1}">
        <td>
          <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=1&searchType=${searchType}&keyword=${keyword}"> 처음 </a>
        </td> 
        <td>
          <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${startnum-1}&searchType=${searchType}&keyword=${keyword}"> 이전 </a>
        </td>      
       </c:if>  

       <c:forEach var="pagenum" begin="${startnum}" end="${endnum}" step="1">
         <c:if test="${pagenum le totPageCount }">
         <td>
           <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${pagenum}&searchType=${searchType}&keyword=${keyword}"
           class="${pagenum eq nowpage ? 'active' : ''}">
           ${pagenum}            
           </a>
         </td>
        </c:if>
       </c:forEach>
        
       <!-- 다음 과 마지막 --> 
       <c:if test="${ endnum lt totPageCount }">
        <td>
          <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${endnum+1}&searchType=${searchType}&keyword=${keyword}"> 다음 </a>
        </td>       
        <td>
          <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${totPageCount}&searchType=${searchType}&keyword=${keyword}"> 마지막 </a>
        </td>
       </c:if>
      </tr>
    </table>
  </div>