/**
 *  Wyszukaj parametry
 */
var $prevSortCol;
var prevSortBy;
var prevSortClass;

var $searchForm = $("#searchForm");
var $sortBy = $searchForm.find("#sortBy");
var $asc = $searchForm.find("#asc");
var $pageSize = $("#pageSize");

/**
 * Picker daty konfiguracji
 */
var start = moment().startOf("year").startOf("month");
var end = moment().endOf("year").endOf("month");

var $daterangepicker = $searchForm.find("#daterange");
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
 * Zainicjuj Daterangepicker z domyślnymi datami Start i End.
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
 * Ustawia sortowanie według kolumny i sprawia, że Ajax wywołuje do sortowania.
 *
 * @param col kolumna do sortowania
 */
function sortBy(col) {
  var $sortCol = $(col);
  var sortBy = $sortCol.attr("sort");
  $sortBy.val(sortBy);
  var sortClass = "";
  var sortClassNew = "";

  if ($sortCol.hasClass("sorting")) {
    sortClass = "sorting";
    sortClassNew = "sorting_asc";
    $asc.val(true);
  } else if ($sortCol.hasClass("sorting_asc")) {
    sortClass = "sorting_asc";
    sortClassNew = "sorting_desc";
    $asc.val(false);
  } else if ($sortCol.hasClass("sorting_desc")) {
    sortClass = "sorting_desc";
    sortClassNew = "sorting_asc";
    $asc.val(true);
  }

  $sortCol.removeClass(sortClass);
  $sortCol.addClass(sortClassNew);

  if (prevSortBy && prevSortBy != sortBy) {
    $prevSortCol.removeClass(prevSortClass);
    $prevSortCol.addClass("sorting");
  }

  $prevSortCol = $sortCol;
  prevSortBy = sortBy;
  prevSortClass = sortClassNew;
}

/**
 * Wyszukiwa się za pomocą metody AJAX.
 */
function search() {
  if (prevSortBy) {
    $prevSortCol.removeClass(prevSortClass);
    $prevSortCol.addClass("sorting");
  }

  $prevSortCol = null;
  prevSortBy = null;
  prevSortClass = null;
}

/**
 * Resekuje formularz wyszukiwania i wyszukiwania za pomocą metody AJAX.
 */
function resetSearch() {
  document.getElementById("searchForm").reset();

  $searchForm.find("#accountIcon").removeClass();
  $searchForm.find("#categoryIcon").removeClass();

  if (prevSortBy) {
    $prevSortCol.removeClass(prevSortClass);
    $prevSortCol.addClass("sorting");
  }

  $prevSortCol = null;
  prevSortBy = null;
  prevSortClass = null;

  $pageSize.find("option:first").prop("selected", "selected");

  initDaterangepicker();
}

/**
 * Przełącza formularz wyszukiwania
 */
function toggleIcon() {
  $("#filterIcon").toggleClass("fa-chevron-down fa-chevron-up");
}
