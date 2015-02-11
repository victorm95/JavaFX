angular.module('sesionService', ['serviceConf', 'ngResource'])

.factory('sesionRest', ['$resource', 'server',
    function($resource, server) {
        return $resource(server+'/usuarios/login/:user', {}, {
            login: {method: 'POST', isArray: false}
        });
    }])

;