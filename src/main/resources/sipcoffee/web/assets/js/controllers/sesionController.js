angular.module('sesion', ['sesionService'])

.controller('loginController', ['$scope', 'sesionRest',
function($scope, sesionRest) {

	$scope.login = function() {
		var toast = document.querySelector("#toast");

		if ($scope.usuario != undefined && $scope.clave != undefined) {
			sesionRest.login({
				usuario: $scope.usuario,
				clave: $scope.clave
			}, function(usuario) {
                sessionStorage.setItem('usuario', usuario);
                window.location = 'index.html';
			}, function(error) {
			    if(error.status == 404) {
			        toast.text = "El usuario y/o contraseña son incorrectos.";
			    } else if(error.status == 403) {
			        toast.text = "El usuario esta inactivo.";
			    }
			    toast.show();
			 });
		}

    };

}])
;