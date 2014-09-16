var terreno = angular.module('terreno', []);

terreno.controller('terrenoController', ['$scope',
function($scope) {

	$scope.mensaje = "";
	$scope.terreno = JSON.parse(terreno.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	/*$scope.terreno = {
		id : 1,
		nombre : 'la bonita',
		direccion:'Avenida el morro',
		area:'11500',
		municipio:{ nombre:'andes'}
	}, {
		id : 2,
		nombre : 'Cosechador'
	}, {
		id : 3,
		nombre : 'Fumigador'
	}*/;
	$scope.selected = new Object();

	$scope.guardar = function() {
		if ($scope.nombre) {
			if( $scope.selected.id != undefined ){
				
				var terreno = terreno.findById( $scope.selected.id );
				terreno.setNombre( $scope.nombre );

				if (terreno.save()) {
					for(var i = 0 ; i < $scope.terreno.length ; i++){
						if( $scope.terreno[i].id == terreno.getId() ){
							$scope.terreno[i] = JSON.parse(terreno.toJson());
							//$scope.$apply();
							break;
						}
					}
				}
				
			}else{
				try {
					var terreno = terreno.create(JSON.stringify({
						nombre : $scope.nombre
					}));
	
	
					if (terreno.save()) {
						$scope.mensaje = "Se ha creado el terreno " + name + " exitosamente.";
						$scope.terreno.push(JSON.parse(terreno.toJson()));
					} else {
						$scope.mensaje = "Ocurrio un error al guradar el terreno " + name + ".";
					}
				} catch(error) {
					console.log(error);
				}
			}
		}

		$scope.nombre = '';
		$scope.selected = new Object();
	};
	
	$scope.eliminar = function(elem){
		var terreno = terreno.findById( elem.id );
		
		if( terreno.delete() ){
			for(var i = 0 ; i < $scope.terreno.length ; i++){
				if( $scope.terreno[i].id == terreno.getId() ){
					$scope.terreno.splice(i, 1);
					//$scope.$apply();
					break;
				}
			}
		}
	};

	$scope.seleccionar = function(elem) {
		$scope.selected = elem;
		$scope.nombre = elem.nombre;
	};
	
	$scope.deseleccionar = function(){
		$scope.nombre = '';
		$scope.selected = new Object();
	};

}]);
