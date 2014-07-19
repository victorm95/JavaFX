var main = angular.module('main', [ 'ngRoute', 'rol', 'usuario' ]);

main.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.
	when('/roles', {
		templateUrl: 'roles.html',
		controller: 'rolController'
	})
	.when('/usuarios', {
		templateUrl: 'usuarios.html',
		controller: 'usuarioController'
	});
	
} ]);