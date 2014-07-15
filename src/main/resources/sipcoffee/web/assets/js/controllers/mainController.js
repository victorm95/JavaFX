var main = angular.module('main', [ 'ngRoute', 'rol' ]);

main.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.
	when('/roles', {
		templateUrl: 'roles.html',
		controller: 'rolController'
	});
	
} ]);