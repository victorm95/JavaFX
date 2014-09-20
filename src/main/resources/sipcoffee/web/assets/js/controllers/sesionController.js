angular.module('sesion', [])

.controller('loginController', ['$scope', '$location',
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
					
					
					document.querySelector('nav').style.visibility = 'visible	';
					
					$location.path('/home');
					
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

}])



.controller('logoutController', ['$location', function($location){
	sessionStorage.clear();
	document.querySelector('nav').style.visibility = 'hidden';	
	$location.path('/');
}])

;
