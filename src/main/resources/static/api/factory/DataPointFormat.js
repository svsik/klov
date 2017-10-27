angular.module('Klov').
factory('DataPointFormat', ['$rootScope', function($rootScope) {
    return {
        getDataPointFormat: function(report, ix, format) {
        	if (typeof format === 'undefined' || format == null)
        		format = $rootScope.trendDataPointFormat;
        	
            switch(format) {
                case 'num':
                    return ix + 1;
                case 'dt':
                    var date = new Date(report.startTime);
                    return (date.getMonth() + 1) + '/' + date.getDate() + '/' +  date.getFullYear();
                case 'name':
                    return report.name;
                default:
                    return (new Date(report.startTime)).toLocaleString().split(",");
            }
        }
    }
}]);
