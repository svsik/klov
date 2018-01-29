angular.module('Klov').    
    factory('BarChartSettings', function() {
    	
    	var datasetOverrideLineBar = [
            {
            	label: 'Duration',
            	type: 'line',
                fill: false,
                borderDash: [3, 2],
                backgroundColor: 'rgba(255,206,86,0.7)',
                borderColor: 'rgba(255,206,86,1)',
                borderWidth: 3,
                borderJoinStyle: 'miter',
                pointBorderColor: '#fff',
                pointBackgroundColor: 'rgba(255,206,86,1)',
                pointBorderWidth: 2,
                pointHoverRadius: 5,
                pointHoverBackgroundColor: 'rgba(255,206,86,1)',
                pointHoverBorderColor: 'rgba(255,206,86,1)',
                pointHoverBorderWidth: 5,
                pointRadius: 5
            },
            {
                label: "Totals",
                borderWidth: 1,
                backgroundColor: '#cdecff',
                borderColor: '#cdecff',
                pointBackgroundColor: '#DAA520',
                pointHoverBackgroundColor: 'rgba(253, 180, 92, .2)',
                pointHoverBorderColor: "rgba(253, 180, 92, .2)",
                type: 'bar'
            },
        ];
    	
        var options = {
            responsive: false,
            maintainAspectRatio: true,
            scales: {
                yAxes: [{
                    gridLines: {
                        display: true,
                        lineWidth: 1,
                    },
                    ticks: {
                        beginAtZero: true,
                        mirror: false,
                        suggestedMin: 0,
                    },
                    stacked: true,
                }],
                xAxes: [{
                    gridLines: {
                        display: false
                    },
                    ticks: {
                        fontSize: 10,
                        beginAtZero: true
                    },
                    barPercentage: 0.1,
                }],
            }
        };
        return {
            options: options,
            datasetOverrideLineBar: datasetOverrideLineBar
        }
    });
