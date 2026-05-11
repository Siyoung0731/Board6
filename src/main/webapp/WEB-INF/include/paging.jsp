<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %> 
  <c:set var="startnum" value="${ sto.pagination.startPage }" />
  <c:set var="endnum" value="${ sto.pagination.endPage }" />
  <c:set var="totPageCount" value="${ sto.pagination.totPageCount }" />
  <div id="paging">
    <table>
      <tr>
<!-- existPage = startPage gt 1 -->
	   <!-- 처음 과 이전 -->
       <c:if test="${ existPrevPage }">
        <td>
          <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=1"> 처음 </a>
          <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${startnum-1}"> 이전 </a>
        </td>       
       </c:if>
        
        <c:forEach var="pagenum" begin="${startnum}" end="${endnum}" step="1">
          <td>
            <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${pagenum}"></a>
            ${pagenum}
          </td>
        </c:forEach>
       <!-- 다음 과 마지막 --> 
       <c:if test="${ existNextPage }">
        <td>
          <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${endnum+1}"> 다음 </a>
          <a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${totPageCount}"> 마지막 </a>
        </td>       
       </c:if>
      </tr>
    </table>
  
  </div>