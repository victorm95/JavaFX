var bloque = angular.module('bloque', []);

bloque.controller('bloqueController', ['$scope',
function($scope) {

	$scope.mensaje = "";
	$scope.bloques = JSON.parse(Bloque.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));

	$scope.selected = new Object();

	$scope.guardar = function() {
		if ($scope.nombre) {

			var bloque = Bloque.create(JSON.stringify({
				nombre : $scope.nombre,		
				area : $scope.area,
				ubicacion : $scope.ubicacion
			}));

			if (bloque.save()) {
				$scope.mensaje = "Se ha creado el bloque " + name + " exitosamente.";
				$scope.bloques.push(JSON.parse(bloque.toJson()));
			} else {
				$scope.mensaje = "Ocurrio un error al guardar el bloque " + name + ".";
			}

		}

		$scope.nombre = '';
		$scope.selected = new Object();
	};

	$scope.eliminar = function(elem) {
		var bloque = Bloque.find(elem.id);

		if( bloque.delete() ) {
			for (var i = 0; i < $scope.bloques.length; i++) {
				if ($scope.bloques[i].id == bloque.getId()) {
					$scope.bloques.splice(i, 1);
					//$scope.$apply();
					break;
				}
			}
		}
	};

}]);
