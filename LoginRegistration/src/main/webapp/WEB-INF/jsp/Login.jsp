<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style type="text/css">
  
	.col-sm-6{
		color:#556B2F;
		margin-top: 50px;
		height: 400;
		width: 300;
	}
  </style>
</head>
<body bgcolor="#FFEFD5">
	<div class="row">
		<div class="col-sm-6">
		<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  		<h2>Login</h2>
       <form:form method="post" action="">    
        <table >
         <tr>    
          <td><input type="email" name="userName" placeholder="Enter username"/></td>
         </tr> 
         <tr>   
          <td><input type="text" name="password" placeholder="Enter password"/></td>  
         </tr>   
         <tr>    
          <td><input type="submit" value="submit" /></td>    
         </tr>    
        </table>    
       </form:form> 
		<a href="Registration">Register</a>
	</div>
</div>
</body>
</html>