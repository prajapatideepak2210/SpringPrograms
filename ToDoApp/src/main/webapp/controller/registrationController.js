
var app = angular.module('ToDo');

app.controller('registrationController',function($scope,registrationService,$location){
	$scope.registerUser= function(){
		var a = registrationService.registerUser($scope.user,$scope.error);
		console.log(a);
			a.then(function(response){
				console.log(response.data.responseMessage);
				localStorage.setItem('token',response.data.responseMessage);
				
				console.log("register success");
				$location.path('login');
			},function(response){
				if(response.status==409)
					{
						$scope.error=response.data.responseMessage;
					}
				else
					{	
						console.log("fail");
						$scope.error="Enter valid data";
					}
			});
	}
});