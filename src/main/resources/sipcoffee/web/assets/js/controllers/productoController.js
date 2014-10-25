var producto = angular.module('producto', []);

producto.controller('productoController', ['$scope',
function($scope) {

    var toast = document.querySelector("#toast");

	$scope.productos = JSON.parse(Producto.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));

	$scope.selected = new Object();

	$scope.guardar = function() {

		if ($scope.nombre) {

			var producto = Producto.create(JSON.stringify({
				nombre : $scope.nombre
			}));

			if (producto.save()) {
				toast.text = "Se ha creado la producto exitosamente.";
				$scope.productos.push(JSON.parse(producto.toJson()));
			} else {
				toast.text = "Ocurrio un error al guardar la parcela.";
			}
            toast.show();
		}

		$scope.nombre = '';
		$scope.selected = new Object();
	};

	$scope.eliminar = function(elem) {
		var producto = Producto.find(elem.id);

		if( producto.delete() ) {
			for (var i = 0; i < $scope.productos.length; i++) {
				if ($scope.productos[i].id == producto.getId()) {
					$scope.productos.splice(i, 1);
					//$scope.$apply();
					break;
				}
			}
		}
	};

}]);
