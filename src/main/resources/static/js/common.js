/**
 * Zwraca wartość parametru URL według nazwy.
 *
 * @param name nazwa parametru URL
 * @returns wartość parametru URL
 */
$.urlParam = function (name) {
  var results = new RegExp("[?&]" + name + "=([^&#]*)").exec(
    window.location.href
  );
  if (results == null) {
    return null;
  } else {
    return results[1] || 0;
  }
};
