var cafeto = angular.module('cafeto', []);

cafeto.controller('cafetoController', ['$scope',
function($scope) {

	var toast = document.querySelector("#toast");

	$scope.cafetos = JSON.parse(Cafeto.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));

	$scope.selected = new Object();

	$scope.guardar = function() {
		if ($scope.nombre) {

			var cafeto = Cafeto.create(JSON.stringify({
				nombre : $scope.nombre,		
				cantidadAbono : parseInt($scope.cantidadAbono),
				cantidadPesticida : parseInt($scope.cantidadPesticida),
				distanciaCafeto : parseInt($scope.distanciaCafeto),
				distanciaSurco : parseInt($scope.distanciaSurco),
				tiempoSemillero : parseInt($scope.tiempoSemillero),
				tiempoAlmacigo : parseInt($scope.tiempoAlmacigo),
				tiempoGraneo : parseInt($scope.tiempoGraneo),
				tiempoSoca : parseInt($scope.tiempoSoca),
				primeraCosecha : parseInt($scope.primeraCosecha),
				proveedor : $scope.proveedor
			}));

			//console.log("Cafeto: " + cafeto.toJson());

            try{
                if (cafeto.save()) {
                    toast.text = "Se ha creado el cafeto " + $scope.nombre + " exitosamente.";
                    $scope.cafetos.push(JSON.parse(cafeto.toJson()));
                } else {
                    console.log("Error guardando: " + error);
                    toast.text = "Ocurrio un error al guardar el cafeto " + $scope.nombre + ".";
                }
			}catch(error){
			    console.log("Catch "+ error);
			    toast.text = "Ocurrio un error al guardar el cafeto " + $scope.nombre + ".";
			}
            toast.show();
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
