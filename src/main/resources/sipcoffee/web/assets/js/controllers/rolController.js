var rol = angular.module('rol', []);

rol.controller('rolController', ['$scope',
function($scope) {

	$scope.mensaje = "";
	//$scope.roles = JSON.parse(Rol.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.placeholder = "Escribe el nombre del Rol";

	$scope.guardar = function(name) {
		try {
			var rol = Rol.create(JSON.stringify({
				nombre : name
			}));
			if (rol.save()) {
				$scope.mensaje = "Se ha creado el Rol " + name + " exitosamente.";
				$scope.roles.push(JSON.parse(rol.toJson()));
			} else {
				$scope.mensaje = "Ocurrio un error al guradar el Rol " + name + ".";
			}
		} catch(error) {
			console.log(error);
		}
	};
		
	$scope.seleccionar = function(elem){
		$scope.placeholder = elem;
	};

}]);
