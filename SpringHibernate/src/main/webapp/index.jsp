<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<html>
<head>
	<style type="text/css">
		.outerdiv{
		margin-top: 50px;
		background-color: lightblue;
		width: 400px;
		height: 300px;
		}
	</style>
</head>
<body bgcolor="pink">
	<div class="outerdiv">
		<h2>Enter the Book</h2>
		<form action="addmethod">
			<table>
				<tr><td> </td><td><input type="hidden" name="bookId"></tr>
				<tr><td>Book Name</td><td><input type="text" name="name" placeholder="Enter the Book Name"></tr>
				<tr><td>Book Price</td><td><input type="number" name="price" placeholder="Enter the Book price"></tr>
				<tr><td><input type="submit" value="submit"></td></tr>
			
			</table>
		</form>
	</div>

</body>
</html>
