var terreno = angular.module('terreno', []);

terreno.controller('terrenoController', ['$scope',
function($scope) {

	$scope.terrenos = JSON.parse(Terreno.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.municipios = JSON.parse(Municipio.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));

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
	};*/
	$scope.selected = new Object();

	$scope.guardar = function() {
		var toast = document.querySelector('#toast');
		
		if ($scope.nombre) {
			if( $scope.selected.id != undefined ){
				
				/*var terreno = Terreno.findById( $scope.selected.id );
				terreno.setNombre( $scope.nombre );

				if (terreno.save()) {
					for(var i = 0 ; i < $scope.terreno.length ; i++){
						if( $scope.terreno[i].id == terreno.getId() ){
							$scope.terreno[i] = JSON.parse(terreno.toJson());
							break;
						}
					}
				}
				*/
			}else{
				try {
					var terreno = Terreno.create(JSON.stringify({
						nombre : $scope.nombre,
						direccion: $scope.direccion,
						area: parseInt($scope.area),
						municipio: parseInt($scope.municipio)
						//imagen: $scope.imagen
					}));
						
					console.log("imagen: " + $scope.imagen);
	
					if (terreno.save()) {
						toast.text = "Se ha creado el terreno " + $scope.nombre + " exitosamente.";
						$scope.terrenos.push( JSON.parse(terreno.toJson()) );
					} else {
						toast.text = "Ocurrio un error al guradar el terreno " + $scope.nombre + ".";
					}
					
					toast.show();
				} catch(error) {
					console.log(error);
				}
			}
		}

		$scope.nombre = '';
		$scope.selected = new Object();
	};
	
	$scope.eliminar = function(elem){
		var terreno = Terreno.findById( elem.id );
		
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
