<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
  
        <h1>Edit Employee</h1>  
       <form:form method="POST" action="editsave">
        <table >    
       <tr>  
        <td></td>    
         <td><input type="hidden" name="id" /></td>
         </tr>
         <tr>
          <td>Name : </td>   
          <td><input type="text" name="name"  /></td>
         </tr>  
         <tr>    
          <td>Designation :</td>    
          <td><input type="text" name="designation" /></td>  
         </tr>   
           
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Edit Save" /></td>    
         </tr>    
        </table>    
       </form:form>    
</body>
</html>