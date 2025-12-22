function showTrendChart(assetDates, totalAssets, moneyReady) {
    document.getElementById('trendChartContainer').style.display = 'block';
    var ctx = document.getElementById('trendChart').getContext('2d');
    // Ensure oldest -> newest so the latest date appears on the right
    var labelsAsc = assetDates.slice().reverse();
    var totalAsc = totalAssets.slice().reverse();
    var readyAsc = moneyReady.slice().reverse();

    if (window.trendChartInstance) {
        window.trendChartInstance.destroy();
    }
    window.trendChartInstance = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labelsAsc,
            datasets: [
                {
                    label: 'Total Asset',
                    data: totalAsc,
                    borderColor: 'rgba(54, 162, 235, 1)',
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    fill: false
                },
                {
                    label: 'Money Ready',
                    data: readyAsc,
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