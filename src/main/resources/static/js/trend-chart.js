function showTrendChart(assetDates, totalAssets, moneyReady) {
    document.getElementById('trendChartContainer').style.display = 'block';
    var ctx = document.getElementById('trendChart').getContext('2d');
    if (window.trendChartInstance) {
        window.trendChartInstance.destroy();
    }
    window.trendChartInstance = new Chart(ctx, {
        type: 'line',
        data: {
            labels: assetDates,
            datasets: [
                {
                    label: 'Total Asset',
                    data: totalAssets,
                    borderColor: 'rgba(54, 162, 235, 1)',
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    fill: false
                },
                {
                    label: 'Money Ready',
                    data: moneyReady,
                    borderColor: 'rgba(75, 192, 192, 1)',
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    fill: false
                }
            ]
        },
        options: {
            responsive: true,
            plugins: {
                legend: { position: 'top' },
                title: { display: true, text: 'Historical Trend: Total Asset & Money Ready' }
            },
            scales: {
                x: { title: { display: true, text: 'Date' } },
                y: { title: { display: true, text: 'Amount ($)' } }
            }
        }
    });
}