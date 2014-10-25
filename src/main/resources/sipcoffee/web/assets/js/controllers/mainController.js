var main = angular.module('main', [ 'ngRoute', 'rol', 'usuario', 'proceso', 'producto', 'terreno', 'bloque', 'parcela', 'cafeto', 'siembra', 'cosecha', 'entrada', 'salida', 'nomina']);

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
	.when('/proceso', {
		templateUrl: 'proceso.html',
		controller: 'procesoController'
	})
	.when('/producto', {
		templateUrl: 'producto.html',
		controller: 'productoController'
	})
	.when('/terreno', {
		templateUrl: 'terreno.html',
		controller: 'terrenoController'
	})
	.when('/bloque', {
		templateUrl: 'bloque.html',
		controller: 'bloqueController'
	})
	.when('/parcela', {
		templateUrl: 'parcela.html',
		controller: 'parcelaController'
	})
	.when('/cafeto', {
		templateUrl: 'cafeto.html',
		controller: 'cafetoController'
	})
	.when('/siembra', {
		templateUrl: 'siembra.html',
		controller: 'siembraController'
	})
	.when('/cosecha', {
		templateUrl: 'cosecha.html',
		controller: 'cosechaController'
	})
	.when('/entrada', {
		templateUrl: 'entrada.html',
		controller: 'entradaController'
	})
	.when('/salida', {
		templateUrl: 'salida.html',
		controller: 'salidaController'
	})
	.when('/nomina', {
		templateUrl: 'nomina.html',
		controller: 'nominaController'
	});
	
} ]);