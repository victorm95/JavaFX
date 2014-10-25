var salida = angular.module('salida', []);

salida.controller('salidaController', ['$scope',
function($scope) {

    var toast = document.querySelector("#paper-toast");

	$scope.salidas = JSON.parse(Salida.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.productos = JSON.parse(Producto.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.usuarios = JSON.parse(Usuario.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));

	$scope.selected = new Object();

	$scope.guardar = function() {
		if ($scope.cantidad) {

			var salida = Salida.create(JSON.stringify({
				cantidad : $scope.cantidad,	
				comentario : $scope.comentario,
				producto : parseInt($scope.producto),
				usuario : parseInt($scope.usuario)
			}));

			if (salida.save()) {
				$scope.mensaje = "Se ha registrado la salida exitosamente.";
				$scope.salidas.push(JSON.parse(salida.toJson()));
			} else {
				toast.text = "Ocurrio un error al guardar el registro.";
				toast.show();
			}

		}

		$scope.cantidad = '';
		$scope.comentario = '';
		$scope.selected = new Object();
	};

	$scope.eliminar = function(elem) {
		var salida = Salida.find(elem.id);

		if( salida.delete() ) {
			for (var i = 0; i < $scope.salidas.length; i++) {
				if ($scope.salidas[i].id == salida.getId()) {
					$scope.salidas.splice(i, 1);
					break;
				}
			}
		}
	};

}]);
