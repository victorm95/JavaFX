var proceso = angular.module('proceso', []);

proceso.controller('procesoController', ['$scope',
function($scope) {

    var toast = document.querySelector("#toast");

	$scope.procesos = JSON.parse(Proceso.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));

	$scope.selected = new Object();

	$scope.guardar = function() {

		if ($scope.nombre) {

			var proceso = Proceso.create(JSON.stringify({
				nombre : $scope.nombre
			}));

			if (proceso.save()) {
				toast.text = "Se ha creado la producto exitosamente.";
				$scope.procesos.push(JSON.parse(proceso.toJson()));
			} else {
				toast.text = "Ocurrio un error al guardar.";
			}
            toast.show();
		}

		$scope.nombre = '';
		$scope.selected = new Object();
	};

	$scope.eliminar = function(elem) {
		var proceso = Proceso.find(elem.id);

		if( proceso.delete() ) {
			for (var i = 0; i < $scope.procesos.length; i++) {
				if ($scope.procesos[i].id == proceso.getId()) {
					$scope.procesos.splice(i, 1);
					//$scope.$apply();
					break;
				}
			}
		}
	};

}]);
