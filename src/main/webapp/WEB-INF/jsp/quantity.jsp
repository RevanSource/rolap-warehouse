<html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <title>Quantity</title>

</head>
<body>
<script type="text/javascript">
    google.charts.load('current', {'packages':['bar']});
    google.charts.setOnLoadCallback(drawStuff);

    function drawStuff() {
        var data = new google.visualization.arrayToDataTable([
            ['Date', 'Quantity'],
            ${line}
        ]);

        var options = {
            title: 'Quantity',
            width: 900,
            legend: { position: 'none' },
            chart: { subtitle: '' },
            axes: {
                x: {
                    0: { side: 'top', label: ''} // Top x-axis.
                }
            },
            bar: { groupWidth: "90%" }
        };

        var chart = new google.charts.Bar(document.getElementById('top_x_div'));
        // Convert the Classic options to Material options.
        chart.draw(data, google.charts.Bar.convertOptions(options));
    };
</script>
<div id="top_x_div" style="width: 900px; height: 300px;"></div>