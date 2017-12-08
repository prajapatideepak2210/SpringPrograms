var app = angular.module('ToDo');
app.factory('loginService', function($http, $location){
	var login ={};
	
	login.loginUser = function(user){
		return $http({
			/*console.log(user);*/
			method : "post",
			url : "login",
			data : user
		})
	}
	return login;
})