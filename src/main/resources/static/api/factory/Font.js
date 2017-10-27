angular.module('Klov').
factory('Font', function() {
    return {
        getFont: function(status) {
            switch (status) {
                case 'pass': return('check');
                case 'fail': return('exclamation-circle');                
                case 'fatal': return('exclamation-circle');
                case 'error': return('exclamation-circle');
                case 'warning': return('exclamation-triangle');
                case 'skip': return('exclamation-circle');                
                case 'info': return('info');
                default:
                    return('');
            };
        },
    }
});
