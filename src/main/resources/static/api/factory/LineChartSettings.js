angular.module('Klov').
    factory('LineChartSettings', function() {
        var options = {
            scaleFontSize: 10,
            scales: {
                yAxes: [{
                    display: true,
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        };

        var datasetOverrideGreen = [
            {
                borderWidth: 1,
                backgroundColor: 'rgba(75,192,192,0.05)',
                borderColor: 'rgba(75,192,192,1)',
                pointBackgroundColor: "#00AF9A",
                pointHoverBackgroundColor: 'rgba(75,192,192,0.1)',
                pointHoverBorderColor: "rgba(75,192,192,0.1)",
                type: 'line'
            },
        ];
        
        var datasetOverrideRegular = [
            {
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
                pointHoverRadius: 2,
                pointHoverBackgroundColor: 'rgba(255,206,86,1)',
                pointHoverBorderColor: '#fff',
                pointHoverBorderWidth: 2,
                pointRadius: 5
            },
        ];

        var datasetOverrideRed = [
            {
                borderWidth: 1,
                backgroundColor: 'rgba(255,90,94,.2)',
                borderColor: 'rgba(255,90,94,.5)',
                pointBackgroundColor: '#FF6347',
                pointHoverBackgroundColor: 'rgba(255,90,94,.2)',
                pointHoverBorderColor: "rgba(255,90,94,.2)",
                type: 'line'
            }
        ];

        var datasetOverride = [
            {
                label: "Pass",
                borderWidth: 1,
                backgroundColor: 'rgba(75,192,192,0.1)',
                borderColor: '#33cc99',
                pointBackgroundColor: "#00AF9A",
                pointHoverBackgroundColor: 'rgba(75,192,192,0.1)',
                pointHoverBorderColor: "rgba(75,192,192,0.1)",
                type: 'line'
            },
            {
                label: "Fail",
                borderWidth: 1,
                backgroundColor: 'rgba(255,90,94,.2)',
                borderColor: 'rgba(255,90,94,.5)',
                pointBackgroundColor: '#FF6347',
                pointHoverBackgroundColor: 'rgba(255,90,94,.2)',
                pointHoverBorderColor: "rgba(255,90,94,.2)",
                type: 'line'
            },
            {
                label: "Others",
                borderWidth: 1,
                backgroundColor: 'rgba(253, 180, 92, .2)',
                borderColor: 'rgba(253, 180, 92, .3)',
                pointBackgroundColor: '#DAA520',
                pointHoverBackgroundColor: 'rgba(253, 180, 92, .2)',
                pointHoverBorderColor: "rgba(253, 180, 92, .2)",
                type: 'line'
            }
        ];

        return {
            options: options,
            datasetOverride: datasetOverride,
            datasetOverrideGreen: datasetOverrideGreen,
            datasetOverrideRed: datasetOverrideRed,
            datasetOverrideRegular: datasetOverrideRegular
        };
    });