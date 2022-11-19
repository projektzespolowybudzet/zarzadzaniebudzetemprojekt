// // To jest JS do tworzenia globalnych funkcji do zdefiniowania nowego pliku cookie na logowanie,
// // lub pobieranie pliku cookie, jeśli użytkownik jest nadal zalogowany.

function checkCookie() {
  if (Cookies.get("id")) {
    return parseInt(Cookies.get("id"));
  } else {
    return false;
  }
}

function setCookie(id) {
  Cookies.set("id", id, { expires: 2 });
}

function deleteCookie() {
  Cookies.remove("id");
}
