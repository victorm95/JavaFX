var parcela = angular.module('parcela', []);

parcela.controller('bloqueController', ['$scope',
function($scope) {

	$scope.mensaje = "";
	$scope.parcelas = JSON.parse(Parcela.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	/*$scope.bloques = {
		id : 1,
		nombre : 'la bonita',
		direccion : 'Avenida el morro',
		area : '11500',
		municipio : {
			nombre : 'andes'
		}
	}, {
	 id : 2,
	 nombre : 'Cosechador'
	 }, {
	 id : 3,
	 nombre : 'Fumigador'
	 };*/
	$scope.selected = new Object();

	$scope.guardar = function() {
		if ($scope.nombre) {

			var parcela = Parcela.create(JSON.stringify({
				nombre : $scope.nombre,		
				area : $scope.area,
				activo : $scope.activo,
				bloque : $scope.bloque
			}));

			if (bloque.save()) {
				$scope.mensaje = "Se ha creado la parcela exitosamente.";
				$scope.parcelas.push(JSON.parse(parcela.toJson()));
			} else {
				$scope.mensaje = "Ocurrio un error al guradar la parcela.";
			}

		}

		$scope.nombre = '';
		$scope.selected = new Object();
	};

	$scope.eliminar = function(elem) {
		var parcela = Parcela.find(elem.id);

		if( parcela.delete() ) {
			for (var i = 0; i < $scope.parcelas.length; i++) {
				if ($scope.parcelas[i].id == bloque.getId()) {
					$scope.parcelas.splice(i, 1);
					//$scope.$apply();
					break;
				}
			}
		}
	};

}]);
