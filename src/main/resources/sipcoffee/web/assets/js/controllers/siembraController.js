var siembra = angular.module('siembra', []);

siembra.controller('siembraController', ['$scope',
function($scope) {

    var toast = document.querySelector("#toast");

	$scope.siembras = JSON.parse(Siembra.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.cafetos = JSON.parse(Cafeto.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.parcelas = JSON.parse(Parcela.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.usuarios = JSON.parse(Usuario.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));

	$scope.selected = new Object();

	$scope.guardar = function() {

		if ($scope.cantidad) {

			var siembra = Siembra.create(JSON.stringify({
				cantidad : $scope.cantidad,	
				cafeto : parseInt($scope.cafeto),
				parcela : parseInt($scope.parcela),
				usuario : parseInt($scope.usuario),
			}));

			if (siembra.save()) {
				toast.text = "Se ha creado la siembra exitosamente.";
				$scope.siembras.push(JSON.parse(siembra.toJson()));
			} else {
				toast.text = "Ocurrio un error al guardar la siembra.";
			}
            toast.show();
		}

		$scope.cantidad = '';
		$scope.selected = new Object();
	};

	$scope.eliminar = function(elem) {
		var siembra = Siembra.find(elem.id);

		if( siembra.delete() ) {
			for (var i = 0; i < $scope.siembras.length; i++) {
				if ($scope.siembras[i].id == siembra.getId()) {
					$scope.siembras.splice(i, 1);
					//$scope.$apply();
					break;
				}
			}
		}
	};

}]);
