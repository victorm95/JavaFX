var entrada = angular.module('entrada', []);

entrada.controller('entradaController', ['$scope',
function($scope) {

    var toast = document.querySelector("#paper-toast");

	$scope.entradas = JSON.parse(Entrada.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.productos = JSON.parse(Producto.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));

	$scope.selected = new Object();

	$scope.guardar = function() {
		if ($scope.cantidad) {

			var entrada = Entrada.create(JSON.stringify({
				cantidad : $scope.cantidad,		
				valor : $scope.valor,
				comentario : $scope.comentario,
				producto : parseInt($scope.producto)
			}));

			if (entrada.save()) {
				$scope.mensaje = "Se ha registrado la entrada exitosamente.";
				$scope.entradas.push(JSON.parse(entrada.toJson()));
			} else {
				toast.text = "Ocurrio un error al guardar el registro.";
				toast.show();
			}

		}

		$scope.cantidad = '';
		$scope.valor = '';
		$scope.comentario = '';
		$scope.selected = new Object();
	};

	$scope.eliminar = function(elem) {
		var entrada = Entrada.find(elem.id);

		if( entrada.delete() ) {
			for (var i = 0; i < $scope.entradas.length; i++) {
				if ($scope.entradas[i].id == entrada.getId()) {
					$scope.entradas.splice(i, 1);
					break;
				}
			}
		}
	};

}]);
