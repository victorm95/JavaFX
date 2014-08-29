angular.module('login', [])

.controller('loginController', [ '$scope', function($scope) {

	$scope.login = function() {
		var toast = document.querySelector("#toast");

		if ($scope.usuario != undefined && $scope.clave != undefined) {
			var usuario = Usuario.login($scope.usuario, $scope.clave);

			if (usuario != null) {
				toast.text = "User: " + usuario.getUsuario();
				toast.show();
			} else {
				toast.text = "El usuario y/o la contraseña con incorrectos.";
				toast.show();
			}

		} else {
			toast.text = "Todos los campos son obligatorios.";
			toast.show();
		}
	};

} ]);
