angular.module("Klov")
    .controller("UserController", ["$scope", "$rootScope", "$http", "$window", '$uibModal', '$timeout', 
    	function($scope, $rootScope, $http, $window, $uibModal, $timeout) {

        $scope.signon = function(query) {
            var req = {
                method: 'POST',
                url: '/signon',
                data: {
                    query: query
                }
            };
            
            $http.defaults.headers.post['X-CSRF-Token'] = $rootScope._csrf;
            
            $http(req).
                success(function(response) {
                    $rootScope.signedOn = true;
                    $rootScope.user = response.user.name;
                    $rootScope.isAdmin = response.user.admin;
          
                    $scope.query = null;
                    
                    var closeModal = function() {
                        $rootScope.modal.close();
                    };
                    $timeout(closeModal, 1000);
                }).
                error(function(response) {
                    $scope.signonSuccess = false;
                });
        };

        $scope.logout = function() {
            $http({
                method: 'GET',
                url: '/logout'
            }).then(function(response) { 
                $rootScope.signedOn = false;
                $rootScope.user = null;
                $rootScope.isAdmin = false;
                
                $window.location.reload();
            });
        };

        $scope.changePassword = function(query) {
            query.user = $scope.user;

            var req = {
                method: 'POST',
                url: '/changePassword',
                data: {
                    query: query
                }
            };

            $scope.query = null;
            
            $http.defaults.headers.post['X-CSRF-Token'] = $rootScope._csrf;
            
            $http(req).
                success(function(response) {
                    $scope.changeSuccess = true;
                    $scope.changeError = null;
                }).
                error(function(response) {
                    $scope.changeError = response.message;
                });
        };

    }]);