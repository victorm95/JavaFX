var main = angular.module('main', [ 'ngRoute', 'rol', 'usuario', 'bloque', 'terreno', 'parcela', 'cafeto']);

main.config([ '$routeProvider', function($routeProvider) {

	$routeProvider
	.when('/', { })
	.when('/roles', {
		templateUrl: 'roles.html',
		controller: 'rolController'
	})
	.when('/usuarios', {
		templateUrl: 'usuarios.html',
		controller: 'usuarioController'
	})	
	.when('/terreno', {
		templateUrl: 'terreno.html',
		controller: 'terrenoController'
	})
	.when('/bloque', {
		templateUrl: 'bloque.html',
		controller: 'bloqueController'
	})
	.when('/cafeto', {
		templateUrl: 'cafeto.html',
		controller: 'cafetoController'
	})
	.when('/siembra', {
		templateUrl: 'siembra.html'
	})
	.when('/entradas', {
		templateUrl: 'entradas.html'
	})
	.when('/salidas', {
		templateUrl: 'salidas.html'
	})
	.when('/parcela', {
		templateUrl: 'parcela.html',
		controller: 'parcelaController'
	})
	.when('/cosecha', {
		templateUrl: 'cosecha.html'
	});
	
} ]);