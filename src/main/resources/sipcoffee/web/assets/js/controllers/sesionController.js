angular.module('sesion', ['sesionService'])

.controller('loginController', ['$scope', 'sesionRest',
function($scope, $location, sesionRest) {
	
	$scope.login = function() {
		var toast = document.querySelector("#toast");

		if ($scope.usuario != undefined && $scope.clave != undefined) {
			var usuario = sesionRest.login({
				usuario: $scope.usuario,
				clave: $scope.clave
			});

			if (usuario != null) {
				if (usuario.activo) {
					toast.text = "Has inicias sesion correctamente.";
					sessionStorage.setItem('usuario', usuario);
					window.location = 'index.html';
				} else {
					toast.text = "El usuario esta Inactivo.";
				}
			} else {
				toast.text = "El usuario y/o la contraseña con incorrectos.";
			}
		} else {
			toast.text = "Todos los campos son obligatorios.";
		}
		toast.show();
	};

}])
;
