angular.module('Klov')
	.controller('CategoryController', ['$rootScope', '$scope', '$http', '$location', 'Icon', 'ChartSettings', 'PieChartSettings', 'BarChartSettings',
    function($rootScope, $scope, $http, $location, Icon, ChartSettings, PieChartSettings, BarChartSettings) {
		
		$scope.pastReportsLength = 10;
		$scope.timeTakenChartType = $scope.testCountChartType = "horizontalBar";
		$scope.activeCategory = null;
		$scope.bdd = false;
		
		$scope.init = function() {
			$scope.getCategoryTimeTakenAverageOverNReports(1);
			$scope.getCategoryTestLengthAverageOverNReports(1);
		};
		
		$scope.getCategoryTimeTakenAverageOverNReports = function(pastReportsLength) {
			$scope.timeTakenSelection = "Showing an average of past " + pastReportsLength + " builds.";
			
			var req = {
                method: 'GET',
                url: '/rest/categories/search/getCategoryTimeTakenAverageOverNReports',
            	params: {
            		reportsLength: pastReportsLength
            	}
            };

            //$http.defaults.headers.post['X-CSRF-Token'] = $rootScope._csrf;

            $http(req).
            success(function(res) {
            	var label = "", duration = 0;
                var labels = [], timeTaken = [];
                
            	for (var ix = 0; ix < res.length; ix++) {
            		label = res[ix].name;
            		duration = res[ix].total;
            		
            		labels.push(label);
            		timeTaken.push(duration);
            	}
            	
            	$scope.timeTakenByTagContainerWidth = document.getElementById("time-taken-by-tag-container").clientWidth - 40;
            	$scope.timeTakenByTagLabels = labels;
            	$scope.timeTakenByTagData = timeTaken;
            	$scope.timeTakenByTagOptions = BarChartSettings.options;

            }).
            error(function(res) {
            	console.log("CategoryController.getCategoryTimeTakenAverageOverNReports error! Message below:");
                console.log(res);
            });
		};
		
		$scope.getCategoryTestLengthAverageOverNReports = function(pastReportsLength) {
			$scope.testLengthSelection = "Showing an average of past " + pastReportsLength + " builds.";
			
			var req = {
                method: 'GET',
                url: '/rest/categories/search/getCategoryTestLengthAverageOverNReports',
            	params: {
            		reportsLength: pastReportsLength
            	}
            };

            //$http.defaults.headers.post['X-CSRF-Token'] = $rootScope._csrf;

            $http(req).
            success(function(res) {
            	var label = "", count = 0;
                var labels = [], testCount = [];
                
            	for (var ix = 0; ix < res.length; ix++) {
            		label = res[ix].name;
            		count = res[ix].total;
            		
            		labels.push(label);
            		testCount.push(count);
            	}
            	
            	$scope.testCountByTagContainerWidth = document.getElementById("test-count-by-tag-container").clientWidth - 40;
            	$scope.testCountByTagLabels = labels;
            	$scope.testCountByTagData = testCount;
            	$scope.testCountByTagOptions = BarChartSettings.options;

            }).
            error(function(res) {
            	console.log("CategoryController.getCategoryTestLengthAverageOverNReports error! Message below:");
                console.log(res);
            });
		}
		
		$scope.changeTimeTakenChartType = function(chart, type) {
			if (type === "bar") {
				if (chart === "timeTaken") {
					$scope.timeTakenChartType = "horizontalBar";
					$scope.timeTakenByTagOptions = BarChartSettings.options;
				}
				if (chart === "testLength") {
					$scope.testCountChartType = "horizontalBar";
					$scope.testCountByTagOptions = BarChartSettings.options;
				}
			}
			
			if (type === "pie") {
				if (chart === "timeTaken") {
					$scope.timeTakenChartType = "doughnut";
					$scope.timeTakenByTagOptions = PieChartSettings.options;
				}
				
				if (chart === "testLength") {
					$scope.testCountChartType = "doughnut";
					$scope.testCountByTagOptions = PieChartSettings.options;
				}
			}
		};
		
	}]);
		
		