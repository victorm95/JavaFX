var main = angular.module('main', [ 'ngRoute', 'rol', 'usuario', 'login']);

main.config([ '$routeProvider', function($routeProvider) {

	$routeProvider
	.when('/', {
		templateUrl: 'login.html',
		controller: 'loginController'
	})	
	.when('/roles', {
		templateUrl: 'roles.html',
		controller: 'rolController'
	})
	.when('/usuarios', {
		templateUrl: 'usuarios.html',
		controller: 'usuarioController'
	});
	
} ]);