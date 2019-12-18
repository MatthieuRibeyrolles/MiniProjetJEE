/* 
 * @author Thibault
 */

/* global google */

// Tuto AJAX: https://stackoverflow.com/questions/4112686/how-to-use-servlets-and-ajax
// Ce tuto est génial, vous devriez le distribuer en annexe :)

google.charts.load('current', {
    'callback': initDoc,
    'packages': ['bar']
});
var m_data;

function valide() {
    var params = {
        date1: $('#startDate').val(),
        date2: $('#endDate').val()
    };
    $.ajax({
        type: "POST",
        url: "AdminServlet",
        data: $.param(params),
        dataType: 'json',
        error: function () {
            console.log("Y'a une erreur Abritu'");
        },
        success: function (responseJson) {
            m_data = responseJson;
            parseAndDraw();
        }
    });
}

function parseAndDraw() {
    $.each(m_data, function (key, value) {
        drawChart(key, value);
    });
}

function drawChart(opt, map) {
    var chart;
    var title;
    var mToL = [];
    var data = new google.visualization.DataTable();
    for (let [key, value] of Object.entries(map)) {
        mToL.push([key, value]);
    }
    mToL.sort((a, b) => b[1] - a[1]);
    if (opt === 'byCategory') {
        title = 'catégorie';
        chart = new google.charts.Bar(document.getElementById('chartByCategory'));
    }
    if (opt === 'byCountry') {
        title = 'pays';
        chart = new google.charts.Bar(document.getElementById('chartByCountry'));
    }
    if (opt === 'byClient') {
        title = 'client';
        chart = new google.charts.Bar(document.getElementById('chartByClient'));
    }

    var options = {
        legend: {position: 'none'},
        chart: {
            legend: 'left',
            title: 'Chiffre d\'affaire par ' + title + ' en patates',
            width: $(window).width() - 30,
            height: $(window).height() / 3,
            format: 'currency',
            is3D: true
        }
    };
    data.addColumn('string', title);
    data.addColumn('number', 'Chiffre d\'affaire');
    data.addRows(mToL);
    chart.draw(data, google.charts.Bar.convertOptions(options));
}

function initDoc() {
    var n = new Date();
    $('#endDate').val(n.getFullYear() + '-' + (n.getMonth() + 1) + '-' + n.getDate());
    valide();
}

$(window).resize(function () {
    if (m_data !== undefined)
        parseAndDraw();
});
