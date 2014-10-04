var parcela = angular.module('parcela', []);

parcela.controller('parcelaController', ['$scope',
function($scope) {

    var toast = document.querySelector("#toast");

	$scope.parcelas = JSON.parse(Parcela.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.bloques = JSON.parse(Bloque.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));

	$scope.selected = new Object();

	$scope.guardar = function() {

		if ($scope.nombre) {

			var parcela = Parcela.create(JSON.stringify({
				nombre : $scope.nombre,		
				area : $scope.area,
				activo : $scope.activo,
				bloque : parseInt($scope.bloque)
			}));

			if (parcela.save()) {
				toast.text = "Se ha creado la parcela exitosamente.";
				$scope.parcelas.push(JSON.parse(parcela.toJson()));
			} else {
				toast.text = "Ocurrio un error al guardar la parcela.";
			}
            toast.show();
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
