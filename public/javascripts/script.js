/*
var eventApp = angular.module('eventApp', []);
createEvent.controller('eventApp', ['$scope', function($scope, $http) {
	$http({
		method: 'POST',
		url: 'http://localhost:9000/createEvent',
		data: parameter
	}).success(function(date, status, headers, config){
		
	}).error(function(date, status, headers, config){
		
	});
}]);
*/
function createEvent($scope, $http){
	$scope.doCreateEvent = function(data){
			$http({
				method: 'POST',
				url: '/createEvent',
				data: data
			}).success(function(date, status, headers, config){
				alert(data);
			}).error(function(date, status, headers, config){
				alert(data);
			});
	}
}