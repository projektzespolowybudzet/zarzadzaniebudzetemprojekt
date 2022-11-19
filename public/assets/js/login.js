$(document).ready(function () {
  var uid;
  checkCookie(function (data) {
    uid = data;
  });
  // Sprawdź, czy użytkownik ma identyfikator logowania.

  if (uid) {
    document.location.href = "/" + uid;

    // kod, kierujący na dashboard.
  } else {
    $(document).on("click", "#loginbtn", function (event) {
      event.preventDefault();
      let email = $("#email").val();
      let password = $("#password").val();

      // poprawność emaila
      if (email.trim() === "") {
        // open error modal
        $(".modal").modal();
        $("#error-body").empty();
        $("#error-body").append("<p>Dodaj e -mail</p>");
        // alert("Dodaj e -mail");
      } else if (password.length < 6) {
        // open error modal
        $(".modal").modal();
        $("#error-body").empty();
        $("#error-body").append("<p>Wprowadź hasło co najmniej 6 znaków</p>");
        //alert("Wprowadź hasło co najmniej 6 znaków");
      } else {
        let userDetails = {
          email: email.trim(),
          password: password.trim(),
        };
        // console.log(userDetails);
        $.post("/login", userDetails).then(function (data) {
          // console.log(data);
          if (data.error) {
            // open error modal
            $(".modal").modal();
            $("#error-body").empty();
            $("#error-body").append("<p>" + data.error + "</p>");
          } else if (data.id) {
            // console.log(data.success);
            setCookie(data.id);
            location.replace("/" + data.id);
            // Ustaw localStorage jako kopię zapasową
            //localStorage.setItem("budget_user_id", data.uid);
          }
        });
      }
    });

    $(document).on("click", "#newUser", function (event) {
      event.preventDefault();
      let preferredName = $("#preferredName").val();
      let email = $("#email").val();
      let phoneNumber = $("#phoneNumber").val();
      // Jeśli walidacja na backend jest nadal włączona, aby wymagać długości 10 - ustaw wartość domyślną
      // if (phoneNumber.length < 10) {
      //     phoneNumber = 5555555555;
      // }
      let password = $("#password").val();

      // Sprawdź poprawność, czy e -mail nie jest pusty
      if (email.trim() === "") {
        // open error modal
        $(".modal").modal();
        $("#error-body").empty();
        $("#error-body").append("<p>Dodaj e -mail</p>");
        //alert("Dodaj e -mail");
      } else if (preferredName.trim() === "") {
        // open error modal
        $(".modal").modal();
        $("#error-body").empty();
        $("#error-body").append("<p>Dodaj imię</p>");
        //alert("Dodaj imię");
      } else if (password.length < 6) {
        // open error modal
        $(".modal").modal();
        $("#error-body").empty();
        $("#error-body").append("<p>Wprowadź hasło co najmniej 6 znaków</p>");
        //alert("Wprowadź hasło co najmniej 6 znaków");
      } else {
        let newUser = {
          preferredName: preferredName.trim(),
          email: email.trim(),
          phoneNumber: phoneNumber,
          password: password,
        };
        // console.log(newUser);
        // console.log("Rządanie post");
        $.post("/signup", newUser).then(function (data) {
          // console.log("data: ", data);
          if (data.id) {
            // Utwórz ciastko
            setCookie(data.id);

            // Ustaw localStorage jako kopię zapasową
            //localStorage.setItem("budget_user_id", data.id);
            location.replace("/profile/" + data.id);
          } else if (data.error) {
            let message = data.error;
            // open error modal
            $(".modal").modal();
            $("#error-body").empty();
            $("#error-body").append(`<p>${message}</p>`);
            //alert(message);
          } else {
            // open error modal
            $(".modal").modal();
            $("#error-body").empty();
            $("#error-body").append(
              `<p>Wystąpił błąd z tym żądaniem. Upewnij się, że poprawnie wypełniłeś formularz.</p>`
            );
          }
        });
        // console.log("Po POST");
      }
    });
  }
});
