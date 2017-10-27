angular.module('Klov').    
    factory('ChartSettings', function() {
        var scales = {
            xAxes: [{
                gridLines: {
                    color: "rgba(232, 232, 232, .8)",
                },
                ticks: {
                    fontSize: 11,
                }
            }],
            yAxes: [{
                gridLines: {
                    color: "rgba(232, 232, 232, .8)",
                },
                ticks: {
                    fontSize: 11,
                    userCallback: function(label, index, labels) {
                        if (Math.floor(label) === label) {
                            return label;
                        }
                    },
                },
                beginAtZero: true,
            }]
        };
        
        return {
            scales: scales,
        }
    });
