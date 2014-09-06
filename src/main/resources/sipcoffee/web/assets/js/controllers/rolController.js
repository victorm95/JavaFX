var rol = angular.module('rol', []);

rol.controller('rolController', ['$scope',
function($scope) {
	
	var toast = document.querySelector('#toast');

	$scope.mensaje = "";
	$scope.roles = JSON.parse(Rol.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	/*$scope.roles = [{
		id : 1,
		nombre : 'Admin'
	}, {
		id : 2,
		nombre : 'Cosechador'
	}, {
		id : 3,
		nombre : 'Fumigador'
	}];*/
	$scope.selected = new Object();

	$scope.guardar = function() {
		if ($scope.nombre) {
			if( $scope.selected.id != undefined ){
				
				var rol = Rol.find( $scope.selected.id );
				rol.setNombre( $scope.nombre );

				if (rol.save()) {
					for(var i = 0 ; i < $scope.roles.length ; i++){
						if( $scope.roles[i].id == rol.getId() ){
							$scope.roles[i] = JSON.parse(rol.toJson());
							
							toast.text = 'Se ha actualizado el rol ' + $scope.roles[i].nombre + '.';
							toast.show();
							
							break;
						}
					}
				}
				
			}else{
				var rol = Rol.create(JSON.stringify({
					nombre : $scope.nombre
				}));


				if (rol.save()) {
					$scope.mensaje = "Se ha creado el Rol " + name + " exitosamente.";
					$scope.roles.push(JSON.parse(rol.toJson()));
					
					toast.text = 'Se ha creado correctamente el rol ' + $scope.nombre + '.';
					toast.show();
					
				} else {
					toast.text = 'Ocurrio un error al guardar el rol.';
					toast.show();
				}
			}
		}

		$scope.nombre = '';
		$scope.selected = new Object();
	};
	
	$scope.eliminar = function(elem){
		var rol = Rol.find( elem.id );
		
		if( rol.delete() ){
			for(var i = 0 ; i < $scope.roles.length ; i++){
				if( $scope.roles[i].id == rol.getId() ){					
					toast.text = 'Se ha eliminado el rol ' + $scope.roles[i].nombre + '.';
					toast.show();
					$scope.roles.splice(i, 1);
					
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
