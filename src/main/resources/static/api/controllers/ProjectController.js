angular.module('Klov')
	.controller('ProjectController', ['$rootScope', '$scope', '$http', '$location', '$window',
    function($rootScope, $scope, $http, $location, $window) {
		
		$scope.assign = function(name) {
			console.log("assign")
            var req = {
                method: 'GET',
                url: 'rest/projects/assign',
                params: {
                	name: name
                }
            };

            //$http.defaults.headers.post['X-CSRF-Token'] = $rootScope._csrf;

            $http(req).
                success(function(res) {
                	window.location.reload();
                }).
                error(function(res) {
                	console.log("ProjectController.assign error! Message below:");
                    console.log(res);
                });
        };
        
        $scope.reset = function() {
            var req = {
                method: 'POST',
                url: 'rest/projects/reset'
            };

            //$http.defaults.headers.post['X-CSRF-Token'] = $rootScope._csrf;

            $http(req).
                success(function(res) {
                	$window.location.href = "/";
                }).
                error(function(res) {
                	console.log("ProjectController.reset error! Message below:");
                    console.log(res);
                });
        };
		
	}]);
		
		