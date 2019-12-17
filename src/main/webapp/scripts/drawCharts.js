/* 
 * @author Thibault
 */

/* global google */

// Tuto AJAX: https://stackoverflow.com/questions/4112686/how-to-use-servlets-and-ajax
// Ce tuto est génial, vous devriez le distribuer en annexe :)

google.charts.load('current', {'packages': ['corechart']});
var m_data;

function valide() {
    var params = {
        date1: $('#startDate').val(),
        date2: $('#endDate').val()
    };
    $.post('AdminServlet', $.param(params), function (responseJson) {
        m_data = responseJson;
        parseAndDraw();
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
    var options = {
        'legend': 'left',
        'title': 'Chiffre d\'affaire par ' + title,
        'width': $(window).width() - 30,
        'height': $(window).height() / 4
    };
    for (let [key, value] of Object.entries(map)) {
        mToL.push([key, value]);
    }
    if (opt === 'byCategory') {
        title = 'catégorie';
        chart = new google.visualization.ColumnChart(document.getElementById('chartByCategory'));
    }
    if (opt === 'byCountry') {
        title = 'pays';
        chart = new google.visualization.ColumnChart(document.getElementById('chartByCountry'));
    }
    if (opt === 'byClient') {
        title = 'client';
        chart = new google.visualization.ColumnChart(document.getElementById('chartByClient'));
    }
    data.addColumn('string', title);
    data.addColumn('number', 'Chiffre d\'affaire');
    data.addRows(mToL);
    chart.draw(data, options);
}

$(document).ready(function() {
    google.charts.load('current', {'packages': ['corechart']});
    valide();
});
$(window).resize(function () {
    if (m_data !== undefined)
        parseAndDraw();
});
