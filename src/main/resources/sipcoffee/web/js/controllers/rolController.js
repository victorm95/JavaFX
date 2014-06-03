var rol = angular.module('rol', []);

rol.controller('rolController', ['$scope',
function($scope) {

	$scope.mensaje = "";

	$scope.guardar = function(name) {
		var rol = Rol.create(name);
		if(rol.save())
			$scope.mensaje = "Se ha creado el Rol exitosamente.";
		else
			$scope.mensaje = "Ocurrio un error al guradar el Rol.";
	};

}]);