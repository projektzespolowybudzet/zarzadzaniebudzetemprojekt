/**
 * Wyszukaj parametry
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
var start = moment().startOf("month");
var end = moment().endOf("month");

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
 * Zainicjuj DangePicker z domyślnymi datami Start i End.
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
 * @param col the column to sort
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

  loadTransactions(1);
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
  loadTransactions(1);
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

  loadTransactions(1);
}

var categoriesLoaded = false;

/**
 * Ładuje plik HTML zawierający opcje kategorii.
 *
 * @param callback
 */
function loadCategories(callback) {
  if (categoriesLoaded) {
    callback();
    return;
  }
  // categories
  var $category = $searchForm.find("#category");
  var categoryUrl =
    ctx + "/userhtml/" + currentUserId + "/category-lookup.html";
  $category
    .html('<i class="ace-icon fa fa-spinner fa-spin orange bigger-125"></i>')
    .load(categoryUrl, function () {
      callback();
    });

  categoriesLoaded = true;
}

var accountsLoaded = false;

/**
 * Ładuje plik HTML zawierający opcje konta.
 *
 * @param callback
 */
function loadAccounts(callback) {
  if (accountsLoaded) {
    callback();
    return;
  }

  // Konta
  var $account = $searchForm.find("#account");
  var accountUrl = ctx + "/userhtml/" + currentUserId + "/account-lookup.html";
  $account
    .html('<i class="ace-icon fa fa-spinner fa-spin orange bigger-125"></i>')
    .load(accountUrl, function () {
      callback();
    });

  accountsLoaded = true;
}

/**
 * Przełącza formularz wyszukiwania
 */
function toggleSearch() {
  $("#filterIcon").toggleClass("fa-chevron-down fa-chevron-up");
  loadCategories(function () {});
  loadAccounts(function () {});
}

/**
 * Ładuje transakcje po stronie.
 *
 * @param page Strona
 */
function loadTransactions(page) {
  var data = $searchForm.serializeArray();
  data.push({ name: "page", value: page });
  data.push({ name: "size", value: $pageSize.find("option:selected").val() });

  $("#resultsBlock")
    .html(
      '<tr><td align="center" colspan="4"><div class="cp-spinner cp-skeleton"></div></td></tr>'
    )
    .load("loadTransactions", data);
}

/**
 * Obciążenia Dodaj szablon transakcji.
 */
function addTransaction() {
  $("#trDialogContentDiv")
    .html('<div class="cp-spinner cp-skeleton"></div>')
    .load("add");
}

/**
 * Obciążenia Dodaj szablon transakcji wsadowych.
 */
function addTransactionBatch() {
  $("#trDialogContentDiv")
    .html('<div class="cp-spinner cp-skeleton"></div>')
    .load("addBatch");
}

/**
 * Ładuje Szablon transakcji.
 */
function viewTransaction(transactionId) {
  $("#trDialogContentDiv")
    .html('<div class="cp-spinner cp-skeleton"></div>')
    .load("view?id=" + transactionId);
}

/**
 * Ładuje szablon transakcji edytuj.
 */
function editTransaction(transactionId) {
  $("#trDialogContentDiv")
    .html('<div class="cp-spinner cp-skeleton"></div>')
    .load("edit?id=" + transactionId);
}
