angular
	.module('Klov', ['chart.js'])
	.config(['$compileProvider', function ($compileProvider) {
	    $compileProvider.imgSrcSanitizationWhitelist(/^\s*(https?|local|data|chrome-extension):/);
	    $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|local|data|chrome-extension):/);
	}]);