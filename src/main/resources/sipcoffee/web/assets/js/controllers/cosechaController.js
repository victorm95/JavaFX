var cosecha = angular.module('cosecha', []);

cosecha.controller('cosechaController', ['$scope',
function($scope) {

    var toast = document.querySelector("#toast");

	$scope.cosechas = JSON.parse(Cosecha.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.parcelas = JSON.parse(Parcela.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.usuarios = JSON.parse(Usuario.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));

	$scope.selected = new Object();

	$scope.guardar = function() {

		if ($scope.cantidad) {

			var cosecha = Cosecha.create(JSON.stringify({
				cantidad : $scope.cantidad,	
				parcela : parseInt($scope.parcela),
				usuario : parseInt($scope.usuario)
			}));

			if (cosecha.save()) {
				toast.text = "Se ha creado la cosecha exitosamente.";
				$scope.cosechas.push(JSON.parse(cosecha.toJson()));
			} else {
				toast.text = "Ocurrio un error al guardar la cosecha.";
			}
            toast.show();
		}

		$scope.cantidad = '';
		$scope.selected = new Object();
	};

	$scope.eliminar = function(elem) {
		var cosecha = Cosecha.find(elem.id);

		if( cosecha.delete() ) {
			for (var i = 0; i < $scope.cosechas.length; i++) {
				if ($scope.cosechas[i].id == cosecha.getId()) {
					$scope.cosechas.splice(i, 1);
					//$scope.$apply();
					break;
				}
			}
		}
	};

}]);
