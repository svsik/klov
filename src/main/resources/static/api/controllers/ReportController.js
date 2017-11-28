angular.module('Klov')
	.controller('ReportController', ['$rootScope', '$scope', '$http', '$location', 'Icon', 'ChartSettings', 'PieChartSettings', 'LineChartSettings', 'BarChartSettings', 'DataPointFormat',
    function($rootScope, $scope, $http, $location, Icon, ChartSettings, PieChartSettings, LineChartSettings, BarChartSettings, DataPointFormat) {
		
		var length = 10;

		$scope.Math = window.Math;
		
        $scope.reportListPagination = false;
        $scope.createGrandChildChart = true;
        $scope.analysisOptions = {
            responsive: false,
            maintainAspectRatio: false,
            scales: ChartSettings.scales
        };

		$scope.getReportList = function(page) {
            $scope.page = page-1;

            var req = {
                method: 'GET',
                url: '/rest/reports/search/findByProjectOrderByStartTimeDesc?page=' + $scope.page
            };

            //$http.defaults.headers.post['X-CSRF-Token'] = $rootScope._csrf;

            $http(req).
            success(function(res) {
                $rootScope.trendDataPointLength = length;
                //$rootScope.trendDataPointFormat = res.dataPointSetting.trendDataPointFormat;

                $scope.reportList = res.content;

                if (length > $scope.reportList.length)
                	length = $scope.reportList.length;
                $scope.length = length;

                var label = "";
                var labels = [], passed = [], failed = [], others = [], total = [], duration = [];

            	$scope.series = [ "Pass", "Fail", "Skipped" ];
                
                for (var ix = length - 1; ix >= 0; ix--) {
                    var report = $scope.reportList[ix];
                    
                    label = DataPointFormat.getDataPointFormat(report, ix, '');
                    labels.push(label);
                    
                    duration.push(Math.round(report.duration / 60));
                    passed.push(report.passParentLength);
                    failed.push(report.failParentLength + report.fatalParentLength + report.errorParentLength + report.warningParentLength);
                    others.push(report.skipParentLength);
                    total.push(passed[passed.length-1] + failed[failed.length-1] + others[others.length-1]);
                }
            	
            	$scope.buildDurationVsTotalsData = [ duration ]; //[ duration, total ];
            	$scope.buildDurationVsTotalsDatasetOverride = BarChartSettings.datasetOverrideLineBar;
		        $scope.buildDurationVsTotalsLabels = labels;
		        $scope.buildDurationVsTotalsOptions = $scope.analysisOptions;
		        $scope.buildDurationVsTotalsContainerWidth = document.getElementById("build-duration-vs-count-container").clientWidth - 40;

		        $scope.buildStatusDatasetOverride = [
		            {
		                label: "Pass",
		                borderWidth: 1,
		                backgroundColor: 'rgba(54, 171, 122, .3)',
		                borderColor: 'rgba(54, 171, 122, .7)',
		                pointBackgroundColor: "#00AF9A",
		                pointHoverBackgroundColor: 'rgba(75,192,192,0.1)',
		                pointHoverBorderColor: "rgba(75,192,192,0.1)",
		                type: 'bar'
		            },
		            {
		                label: "Fail",
		                borderWidth: 1,
		                backgroundColor: 'rgba(233, 89, 91, .3)',
		                borderColor: 'rgba(233, 89, 91, .7)',
		                pointBackgroundColor: '#FF6347',
		                pointHoverBackgroundColor: 'rgba(255,90,94,.2)',
		                pointHoverBorderColor: "rgba(255,90,94,.2)",
		                type: 'bar'
		            },
		            {
		                label: "Skipped",
		                borderWidth: 1,
		                backgroundColor: 'rgba(236, 153, 64, .3)',
		                borderColor: 'rgba(236, 153, 64, .7)',
		                pointBackgroundColor: '#DAA520',
		                pointHoverBackgroundColor: 'rgba(253, 180, 92, .2)',
		                pointHoverBorderColor: "rgba(253, 180, 92, .2)",
		                type: 'bar'
		            },
		            {
		                label: "Totals",
		                type: 'line',
		                fill: false,
		                borderDash: [3, 2],
		                backgroundColor: 'rgba(255,206,86,0.7)',
		                borderColor: 'rgba(255,206,86,1)',
		                borderWidth: 2,
		                borderJoinStyle: 'miter',
		                pointBorderColor: '#fff',
		                pointBackgroundColor: 'rgba(255,206,86,1)',
		                pointBorderWidth: 2,
		                pointHoverRadius: 2,
		                pointHoverBackgroundColor: 'rgba(255,206,86,1)',
		                pointHoverBorderColor: '#fff',
		                pointHoverBorderWidth: 2,
		                pointRadius: 5
		            }
		        ];
                $scope.buildStatusLabels = labels;
                $scope.buildStatusData = [ passed, failed, others, total ];	// past reports chart
                $scope.width = document.getElementById("build-status-container").clientWidth - 40;	// past reports chart
            }).
            error(function(response) {
            	console.log("ReportController.getReportList error! Message below:");
                console.log(response);
            });
        };
        
        $scope.getPeriodReportAggregation = function(daysPast) {
    		$scope.daysPast = daysPast;
        	
        	var req = {
                method: 'GET',
                url: '/rest/reports/search/getPeriodReportAggregation',
                params: {
                	daysPast: daysPast
                }
            };
        	
        	$http(req).
        	success(function(res) {
        		var labels = [], passed = [], failed = [], skipped = [], total = [];
        		var failedLength = 0;
        		var label;
        		
        		for (var ix = res.length-1; ix >= 0; ix--) {
        			label = DataPointFormat.getDataPointFormat(res[ix], ix, 'dt');
        			labels.push(label);
                    total.push(res[ix].total);
                    passed.push({ x: 0, y: res[ix].passTotal, r: (res[ix].passTotal/res[ix].total)*30 });
                    skipped.push({ x: 0, y: res[ix].skipTotal, r: (res[ix].skipTotal/res[ix].total)*30 });
                    
                    failedLength = res[ix].total-(res[ix].passTotal+res[ix].skipTotal)
                    failed.push({ x: 0, y: failedLength, r: (failedLength/res[ix].total)*30 });
                }
        		$scope.periodReportAggregationData = [ passed, skipped, failed, total ];
        		$scope.periodReportAggregationLabels = labels;
        		$scope.periodReportAggregationOverride = [
        			{
                    	label: "Passed",
                    	type: "bubble",
                    	backgroundColor: "rgba(54, 171, 122, .3)"
                    }, {
                    	label: "Skipped",
                    	type: "bubble",
                    	backgroundColor: "rgba(253, 180, 92, .6)"
                    }, {
                    	label: "Failed",
                    	type: "bubble",
                    	backgroundColor: "rgba(233, 89, 91, .7)"
                    }, {
                        label: "Total",
                        type: 'bar',
                        backgroundColor: '#cdecff',
                        borderColor: '#cdecff',
                        borderWidth: 1,
                        width: 0.1,
                        borderJoinStyle: 'miter'
                    }
        		];
        		$scope.periodReportAggregationOptions = {
    				scales: {
						xAxes:[{
							barPercentage: 0.1
						}],
						yAxes:[{
							display: true
						}]
    				},
    				legend: { position: 'bottom' }
				};
        	}).
        	error(function(res) {
            	console.log("ReportController.getPeriodReportAggregation error! Message below:");
                console.log(res);
            });
        };
        
        $scope.getReportListByCategoryName = function(name) {
        	$scope.category = name;
        	
        	var req = {
                method: 'GET',
                url: '/rest/reports/search/findByCategoryNameList',
                params: {
                	name: name
                }
            };
        	
        	$http(req).
        	success(function(res) {
        		$scope.reportListByCategory = res;        		
        	}).
        	error(function(response) {
            	console.log("ReportController.getReportListByCategoryName error! Message below:");
                console.log(response);
            });
        };
        
        $scope.remove = function(id, el) {
        	var req = {
                method: 'DELETE',
                url: '/rest/reports/remove/' + id,
                params: {
                	id: id
                }
            };
        	
        	$http(req).
        	success(function(res) {
        		console.log(res)
    			if (res)
    				$scope[el] = true;
        	}).
        	error(function(res) {
            	console.log("ReportController.remove error! Message below:");
                console.log(res);
            });
        };
		
	}]);
		
		