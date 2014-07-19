var rol = angular.module('usuario', []);

rol.controller('usuarioController', ['$scope',
function($scope) {

	$scope.roles = JSON.parse(Rol.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.usuarios = JSON.parse(Usuario.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.activo = true;
	/*$scope.usuarios = [
	 {
	 rol: 'Admin',
	 nombre: 'Juanito',
	 cedula: 123456,
	 direccion: 'Calle 100',
	 municipio: 'Medellin',
	 telefono: 1234567,
	 activo: true
	 }
	 ];*/

	$scope.guardar = function() {
		if ($scope.nombre != undefined && $scope.usuario != undefined && $scope.clave1 != undefined && $scope.clave2 != undefined && $scope.cedula != undefined && $scope.expedicion != undefined && $scope.direccion != undefined && $scope.telefono != undefined) {
			if ($scope.clave1 == $scope.clave2) {
				var usuario = Usuario.create(JSON.stringify({
					nombre : $scope.nombre,
					usuario : $scope.usuario,
					rol : $scope.rol,
					clave : $scope.clave1,
					cedula : $scope.cedula,
					expedicionDocumento : $scope.expedicion,
					direccion : $scope.direccion,
					telefono : $scope.telefono,
					activo : $scope.activo
				}));

				if (usuario.save()) {
					$scope.usuarios.push(JSON.parse(usuario.toJson()));
					document.querySelector("form").reset();
				}

			}
		}
	};

	$scope.eliminar = function(elem) {
		var usuario = Usuario.find(elem.id);

		if( usuario.delete() ) {
			for (var i = 0; i < $scope.usuarios.length; i++) {
				if ($scope.usuarios[i].id == usuario.getId()) {
					$scope.usuarios.splice(i, 1);
					//$scope.$apply();
					break;
				}
			}
		}
	};

}]);
