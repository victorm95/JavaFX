var rol = angular.module('usuario', []);

rol.controller('usuarioController', ['$scope',
function($scope) {

	$scope.roles = JSON.parse(Rol.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.usuarios = JSON.parse(Usuario.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.activo = true;
	/*$scope.usuarios = [
	 {
	 rol: 'Admin',
	 nombre: 'Juanito',
	 cedula: 123456,
	 direccion: 'Calle 100',
	 municipio: 'Medellin',
	 telefono: 1234567,
	 activo: true
	 }
	 ];*/

	$scope.guardar = function() {
		var toast = document.querySelector('#toast');
		if ($scope.nombre != undefined && $scope.usuario != undefined && $scope.clave1 != undefined && $scope.clave2 != undefined && $scope.cedula != undefined && $scope.expedicion != undefined && $scope.direccion != undefined && $scope.telefono != undefined) {
			if ($scope.clave1 == $scope.clave2) {
				
				if( isNaN(Date.parse($scope.expedicion)) ){
					toast.text = "La fecha de expedición no es valida.";
					toast.show();
					return;
				}
				
				var usuario = Usuario.create(JSON.stringify({
					nombre : $scope.nombre,
					usuario : $scope.usuario,
					rol : $scope.rol,
					clave : $scope.clave1,
					cedula : $scope.cedula,
					expedicionDocumento : Date.parse($scope.expedicion),
					direccion : $scope.direccion,
					telefono : $scope.telefono,
					activo : $scope.activo
				}));

				if (usuario.save()) {
					$scope.usuarios.push(JSON.parse(usuario.toJson()));
					document.querySelector("form").reset();
					
					toast.text = "Se ha registrado al usuario " + usuario.getNombre() + ".";
					toast.show();
				}else{
					toast.text = "Ha ocurrido un error al registrar al usuario.";
					toast.show();
				}

			}else{
				toast.text = "Las contraseñas no coinciden.";
				toast.show();
			}
		}else{
			toast.text = "Todos lo campos son obligatorios.";
			toast.show();
		}
	};

	$scope.desactivar = function(usuario){
		var toast = document.querySelector('#toast');
		var user = Usuario.find(usuario.id);
		
		user.setActivo( !usuario.activo );
		
		if( user.save() )
			toast.text = "Se "+ ( !usuario.activo ? "Habilito" : "Inhabilito" ) + " al usuario " + usuario.nombre + ".";
		else
			toast.text = "Ocurrio un error al inhabilitar al usuario " + usuario.nombre + ".";
		
		toast.show();
	};

}]);