$pieChartForm = $("#pieChartForm");
var placeholder = $("#piechart-placeholder").css({
  width: "90%",
  "min-height": "250px",
});
var plot;

/**
 * Wybór daty konfiguracji
 */
var start = moment().startOf("month");
var end = moment().endOf("month");

var $daterangepicker = $pieChartForm.find("#daterange");
$daterangepicker.daterangepicker(
  {
    startDate: start,
    endDate: end,
    ranges: {
      Dziś: [moment(), moment()],
      Wczoraj: [moment().subtract(1, "days"), moment().subtract(1, "days")],
      "Ostatnie 7 dni": [moment().subtract(6, "days"), moment()],
      "Bieżący miesiąc": [moment().startOf("month"), moment().endOf("month")],
      "Bieżący rok": [moment().startOf("year").startOf("month"), moment()],
    },
  },
  cb
);

/**
 * Zainicjuj wybór przedziału dat z domyślnymi datami Start i End.
 */
function initDaterangepicker() {
  cb(start, end);
}

/**
 * Ustawia dane daty początkowego i końcowego do zbieracza zasięgu.
 *
 * @param start data rozpoczęcia
 * @param end data końcowa
 */
function cb(start, end) {
  $daterangepicker.text(
    start.format("DD/MM/YYYY") + " - " + end.format("DD/MM/YYYY")
  );
}

/**
 * Zainicjuj wykres kołowy.
 */
function initPieChart() {
  $(".easy-pie-chart.percentage").each(function () {
    var $box = $(this).closest(".infobox");
    var barColor =
      $(this).data("color") ||
      (!$box.hasClass("infobox-dark")
        ? $box.css("color")
        : "rgba(255,255,255,0.95)");
    var trackColor =
      barColor == "rgba(255,255,255,0.95)"
        ? "rgba(255,255,255,0.25)"
        : "#E2E2E2";
    var size = parseInt($(this).data("size")) || 50;
    $(this).easyPieChart({
      barColor: barColor,
      trackColor: trackColor,
      scaleColor: false,
      lineCap: "butt",
      lineWidth: parseInt(size / 10),
      animate: ace.vars["old_ie"] ? false : 1000,
      size: size,
    });
  });

  var data = [];
  placeholder.data("chart", data);
  placeholder.data("draw", drawPieChart);

  // Przykład tabel wykresu kołowego
  var $tooltip = $(
    "<div class='tooltip top in'><div class='tooltip-inner'></div></div>"
  )
    .hide()
    .appendTo("body");
  var previousPoint = null;

  placeholder.on("plothover", function (event, pos, item) {
    if (item) {
      if (previousPoint != item.seriesIndex) {
        previousPoint = item.seriesIndex;
        var series = item.series;
        var percent = parseFloat(series.percent).toFixed(2);
        var tip =
          series["label"] +
          " : " +
          percent +
          "%" +
          " (" +
          series["total"] +
          series["symbol"] +
          ")";
        $tooltip.show().children(0).text(tip);
      }
      $tooltip.css({ top: pos.pageY + 10, left: pos.pageX + 10 });
    } else {
      $tooltip.hide();
      previousPoint = null;
    }
  });
}

/**
 * Ładuje dane wykresu kołowego za pośrednictwem AJAX.
 */
function loadPieChartData() {
  $.ajax({
    type: "POST",
    url: "dashboard/pieChartData",
    data: $pieChartForm.serializeArray(),
    success: function (response) {
      var result = response.result;
      var list = result.list;
      var chartData = [];

      for (var i = 0; i < list.length; i++) {
        var tr = list[i];
        var catTransaction = {};
        catTransaction.label = tr.category;
        catTransaction.data = tr.percent;
        catTransaction.total = tr.totalStr;
        catTransaction.symbol = tr.symbol;
        catTransaction.color = tr.categoryColor;
        chartData.push(catTransaction);
      }
      $("#pieChartTotal").text(result.totalStr);

      if (chartData.length > 0) {
        try {
          drawPieChart(placeholder, chartData);
        } catch (e) {}
      } else {
        placeholder.empty();
      }
    },
  });
}

/**
 * Rysuje wykres kołowy na podstawie podanych danych.
 *
 * @param placeholder Zasób zastępczy wykresu kołowego
 * @param data Dane wykresu
 * @param position pozycja
 */
function drawPieChart(placeholder, data, position) {
  plot = $.plot(placeholder, data, {
    series: {
      pie: {
        show: true,
        tilt: 1,
        highlight: {
          opacity: 0.25,
        },
        stroke: {
          color: "#fff",
          width: 2,
        },
        startAngle: 2,
      },
    },
    legend: {
      show: true,
      container: "#legendholder",
      position: position || "ne",
      labelBoxBorderColor: null,
      margin: [-30, 15],
      labelFormatter: function (label, series) {
        var percent = Math.round(series.percent);
        var number = series.data[0][1];
        return (
          "&nbsp;<b>" +
          label +
          "</b>:&nbsp;" +
          percent +
          "%" +
          " (" +
          series["total"] +
          series["symbol"] +
          ")"
        );
      },
    },
    grid: {
      hoverable: true,
      clickable: true,
    },
  });
}

/**
 * Ładuje konta przeglądające dane.
 */
function loadAccountsOverview() {
  $.ajax({
    type: "POST",
    url: "dashboard/accountsOverview",
    data: $("#accountsForm").serializeArray(),
    success: function (response) {
      var result = response.result;
      var html = "";

      for (var i = 0; i < result.length; i++) {
        var acc = result[i];
        html += '<div class="infobox accountOverviewWidget">';
        html +=
          '<div class="infobox-icon accountCircle" style="background-color: ' +
          acc.color +
          '";>';
        html += '<span class="fa-icon">' + acc.icon + "</span>";
        html += "</div>";
        html += '<div class="infobox-data">';
        html +=
          '<span class="infobox-data-number">' +
          acc.balance +
          "" +
          acc.symbol +
          "</span>";
        html += '<div class="infobox-content">' + acc.name + "</div>";
        html += "</div>";
        html += "</div>";
      }
      $("#accountsBody").html(html);
    },
  });
}

/**
 * Pobieranie wykresu kołowego jako pdf.
 */
function downloadPieChartData() {
  var canvas = plot.getCanvas();
  var src = canvas.toDataURL("image/png");
  var pdf = new jsPDF("p", "pt", "a4");
  pdf.addImage(src, "PNG", 0, 0);
  pdf.fromHTML($("#legendholder").html(), 0, 250);
  pdf.save("transactions_chart.pdf");
}
