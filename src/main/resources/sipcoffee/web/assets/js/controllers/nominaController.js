var nomina = angular.module('nomina', []);

nomina.controller('nominaController', ['$scope',
function($scope) {

    var toast = document.querySelector("#toast");

	$scope.nominas = JSON.parse(Nomina.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));

	$scope.selected = new Object();

	$scope.guardar = function() {

		if ($scope.horas && $scope.valor) {

			var nomina = Nomina.create(JSON.stringify({
				horas : $scope.horas,
				valor : $scope.valor,
				proceso : parseInt($scope.proceso),
				usuario : parseInt($scope.usuario)
			}));

			if (nomina.save()) {
				toast.text = "Se ha creado la nomina exitosamente.";
				$scope.nominas.push(JSON.parse(nomina.toJson()));
			} else {
				toast.text = "Ocurrio un error al guardar.";
			}
            toast.show();
		}

		$scope.horas = '';
		$scope.valor = '';
		$scope.selected = new Object();
	};

	$scope.eliminar = function(elem) {
		var nomina = Nomina.find(elem.id);

		if( nomina.delete() ) {
			for (var i = 0; i < $scope.nominas.length; i++) {
				if ($scope.nominas[i].id == nomina.getId()) {
					$scope.nominas.splice(i, 1);
					//$scope.$apply();
					break;
				}
			}
		}
	};

}]);
