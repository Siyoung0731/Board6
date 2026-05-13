<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
<link href="/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<link href="/css/common.css" rel="stylesheet" /> 
<style>
	 table { width:100%; }
	 td {
	    padding:5px 10px;
	    text-align : center;
	    &:nth-of-type(1) {
	     background: black;
	     color : white;
	     border:1px solid white;
	  } 
	}
	#table1 {
	margin-bottom: 150px;
       td {
        &:nth-of-type(1) {
           width : 150px;  
           background: black;
	       color : white;         
        }
        &:nth-of-type(2) {
           width : 150px;
           background: white;
	       color : black;           
        }
        &:nth-of-type(3) {
           width : 150px;
           background: black;
	       color : white; 
	       border-bottom : 1px solid white;          
        }
        &:nth-of-type(4) {
           width : 150px;
           background: white;
	       color : black;                  
        }
   	}
}
	input[type=text], input[type=number], input[type=password] {
		width: 100%;
	}
	input[type=submit], input[type=button] {
		width: 150px;
		height: 30px;
	}
	input[name=usreid] {
		width: 65%;
	}
	 #table1  tr:last-of-type > td {
	     background: white;
	     border : 1px solid black; 
	 }  
	 #table1  tr:nth-of-type(3) td:nth-of-type(2) { 
	     text-align: left;
	 }
	 #table1  tr:nth-of-type(4) {
	     height : 50px;
	     td:nth-of-type(2) {
	        text-align:left;
	        vertical-align: baseline;
	     }
	 }
	textarea {
		width: 100%;
		height: 400px;
	}
	input[name="title"], textarea {
		padding: 5px;
	}
</style>
</head>
<body>
	<main>
		<%@include file="/WEB-INF/include/menuspaging.jsp" %>
		<h2>${menu_name} 게시글 수정</h2>
		<form action="/BoardPaging/Update?idx=${board.idx}&menu_id=${menu_id}&nowpage=${nowpage}" method="post">
		  <table id="table1">
  		    <tr>
		      <td><span class="red">*</span>게시글 번호</td>
		      <td>
		      	<input type="text" name="idx" value="${board.idx}" readonly />
		      </td>
		    </tr>
		    <tr>
		      <td><span class="red">*</span>제목</td>
		      <td colspan="3">
		      	<input type="text" name="title" value="${board.title}"/>
		      </td>
		    </tr>
   		    <tr>
		      <td>수정할 내용</td>
		      <!-- textarea 는 value 에 값을 안 넣어주고 바깥부분에 값을 넣어줌 -->
		      <td colspan="3"><textarea name="content" >${board.content}</textarea></td>
		    </tr>
		    <tr>
		      <td colspan="2">
		      	<input type="submit" value="수정" />
		      	<input type="button" value="목록" 
		      	onclick="window.location.href='/BoardPaging/List?idx=${idx}&menu_id=${menu_id}&nowpage=${nowpage}'"/>
		      </td>
		    </tr>
		  </table>
		</form>
	</main>
	<!-- JavaScript 코딩 : client validation -->
	<script>
		
	</script>
</body>
</html>






















