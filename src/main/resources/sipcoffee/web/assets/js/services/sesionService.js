angular.module('sesionService', ['ngResource', 'serviceConf'])

.factory('sesionRest', ['$resource', 'server',
    function($resource, server) {
        return $resource(server+'/usuarios/login/', {}, {
            login: {method: 'POST', isArray: false}
        });
    }])

;