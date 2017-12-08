/**
 * 
 */
var app = angular.module('ToDo');
app.factory('registrationService', function($http, $location){
	var login ={};
	
	login.loginUser = function(user){
		return $http({
			method : "post",
			url : "register",
			data : user
		})
	}
	return login;
})