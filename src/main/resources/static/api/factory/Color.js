angular.module('Klov').
factory('Color', function() {
    return {
        getColor: function(status) {
            switch (status) {
                case 'pass': return('green');
                case 'fail': return('red');                
                case 'fatal': return('red');
                case 'error': return('amber');
                case 'warning': return('yellow');
                case 'skip': return('yellow');                
                case 'info': return('light-blue');
                default:
                    return('');
            };
        },
        
        getBootstrapColor: function(status) {
        	switch (status) {
	            case 'pass': return('success');
	            case 'fail': return('danger');                
	            case 'fatal': return('danger');
	            case 'error': return('danger');
	            case 'warning': return('warning');
	            case 'skip': return('warning');                
	            case 'info': return('info');
	            default:
	                return('');
	        };
        }
    }
});
