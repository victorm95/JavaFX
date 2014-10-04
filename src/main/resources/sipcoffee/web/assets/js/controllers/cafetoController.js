var cafeto = angular.module('cafeto', []);

cafeto.controller('cafetoController', ['$scope',
function($scope) {

	$scope.mensaje = "";
	$scope.cafetos = JSON.parse(Cafeto.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));

	$scope.selected = new Object();

	$scope.guardar = function() {
		if ($scope.nombre) {

			var cafeto = Cafeto.create(JSON.stringify({
				nombre : $scope.nombre,		
				cantidadAbono : $scope.cantidadAbono,
				cantidadPesticida : $scope.cantidadPesticida,
				distanciaCafeto : $scope.distanciaCafeto,
				distanciaSurco : $scope.distanciaSurco,
				tiempoSemillero : $scope.tiempoSemillero,
				tiempoAlmacigo : $scope.tiempoAlmacigo,
				tiempoGraneo : $scope.tiempoGraneo,
				tiempoSoca : $scope.tiempoSoca,
				primeraCosecha : $scope.primeraCosecha,
				proveedor : $scope.proveedor
			}));

			if (bloque.save()) {
				$scope.mensaje = "Se ha creado el cafeto " + name + " exitosamente.";
				$scope.cafetos.push(JSON.parse(cafeto.toJson()));
			} else {
				$scope.mensaje = "Ocurrio un error al guardar el cafeto " + name + ".";
			}

		}

		$scope.nombre = '';
		$scope.selected = new Object();
	};

	$scope.eliminar = function(elem) {
		var cafeto = Cafeto.find(elem.id);

		if( cafeto.delete() ) {
			for (var i = 0; i < $scope.cafetos.length; i++) {
				if ($scope.cafetos[i].id == cafeto.getId()) {
					$scope.cafetos.splice(i, 1);
					//$scope.$apply();
					break;
				}
			}
		}
	};

}]);
