var rol = angular.module('usuario', []);

rol.controller('usuarioController', ['$scope', '$location',
function($scope, $location) {

	if (sessionStorage.length == 0)
		$location.path('/');

	$scope.roles = JSON.parse(Rol.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.usuarios = JSON.parse(Usuario.all().toString().replace(/\"/g, "").replace(/\\/g, '"'));
	$scope.activo = true;
	$scope.seleccion = null;
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

				if (isNaN(Date.parse($scope.expedicion))) {
					toast.text = "La fecha de expedición no es valida.";
					toast.show();
					return;
				}

				if ($scope.seleccion != null) {

					var usuario = Usuario.find( $scope.seleccion.id );
					console.log( '[Usuario]: ' + usuario.toJson());
					
					usuario.setNombre( $scope.nombre );
					usuario.setCedula( $scope.cedula );
					usuario.setDireccion( $scope.direccion );
					usuario.setTelefono( $scope.telefono );
					usuario.setUsuario( $scope.usuario );
					usuario.setClave( $scope.clave1 );
					usuario.setExpedicionDocumento( Date.parse($scope.expedicion) );
					usuario.setRol( $scope.rol );
					usuario.setActivo( $scope.activo );
					
					if( usuario.save() )
						toast.text = 'El usuario se ha actualizado correctamente.';
					else
						toast.text = 'currio un error al actualizar el rol.';
					
					toast.show();

				} else {

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
					} else {
						toast.text = "Ha ocurrido un error al registrar al usuario.";
						toast.show();
					}

				}

			} else {
				toast.text = "Las contraseñas no coinciden.";
				toast.show();
			}
		} else {
			toast.text = "Todos lo campos son obligatorios.";
			toast.show();
		}
	};

	$scope.desactivar = function(usuario) {
		var toast = document.querySelector('#toast');
		var user = Usuario.find(usuario.id);

		user.setActivo(!usuario.activo);

		if (user.save())
			toast.text = "Se " + (!usuario.activo ? "Habilito" : "Inhabilito" ) + " al usuario " + usuario.nombre + ".";
		else
			toast.text = "Ocurrio un error al inhabilitar al usuario " + usuario.nombre + ".";

		toast.show();
	};

	$scope.seleccionar = function(usuario) {
		$scope.seleccion = usuario;

		$scope.rol = usuario.rol.nombre;
		$scope.nombre = usuario.nombre;
		$scope.usuario = usuario.usuario;
		$scope.cedula = usuario.cedula;
		$scope.direccion = usuario.direccion;
		$scope.telefono = usuario.telefono;
		$scope.activo = usuario.activo;
	};

	$scope.deseleccionar = function() {
		$scope.seleccion = null;
		$scope.activo = true;
	};

}]); 