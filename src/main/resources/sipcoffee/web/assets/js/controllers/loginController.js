angular.module('login', []).controller('loginController', ['$scope', '$location',
function($scope, $location) {
	
	$scope.login = function() {
		var toast = document.querySelector("#toast");

		if ($scope.usuario != undefined && $scope.clave != undefined) {
			var usuario = Usuario.login($scope.usuario, $scope.clave);

			if (usuario != null) {
				if (usuario.isActivo()) {
					toast.text = "Has inicias sesion correctamente.";
					toast.show();
					
					sessionStorage.setItem('usuario', usuario.toJson());
					
					$location.path('/roles');
					
				} else {
					toast.text = "El usuario esta Inactivo.";
					toast.show();
				}
			} else {
				toast.text = "El usuario y/o la contraseña con incorrectos.";
				toast.show();
			}

		} else {
			toast.text = "Todos los campos son obligatorios.";
			toast.show();
		}
	};

}]);
