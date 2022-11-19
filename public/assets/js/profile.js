$(document).ready(function () {
  var uid = checkCookie();
  if (!uid) {
    document.location.href = "/login";
  }

  $(".dashboardLink").attr("href", `/${uid}`);
  $(".profileLink").attr("href", `/profile/${uid}`);

  // let uid = localStorage.getItem("budget_user_id")
  // console.log(uid)

  $(document).on("click", "#profileEdit", function () {
    // define field answers as new answer || placeholder from database
    // performing a validation as conditionals to prevent synchronicity issue with function
    let preferredName = $("#preferredName").val();
    if (!preferredName) {
      preferredName = $("#preferredName").attr("placeholder");
    }
    let email = $("#email").val();
    if (!email) {
      email = $("#email").attr("placeholder");
    }
    let phone = $("#phone").val();
    if (!phone) {
      phone = $("#phone").attr("placeholder");
    }
    let password = $("#password").val();
    if (!password) {
      password = $("#password").attr("placeholder");
    }
    let income = $("#income").val();
    if (!income) {
      income = $("#income").attr("placeholder");
    }
    let cat0name = $("#cat0name").val();
    if (!cat0name) {
      cat0name = $("#cat0name").attr("placeholder");
    }
    let cat1name = $("#cat1name").val();
    if (!cat1name) {
      cat1name = $("#cat1name").attr("placeholder");
    }
    let cat2name = $("#cat2name").val();
    if (!cat2name) {
      cat2name = $("#cat2name").attr("placeholder");
    }
    let cat3name = $("#cat3name").val();
    if (!cat3name) {
      cat3name = $("#cat3name").attr("placeholder");
    }
    let cat4name = $("#cat4name").val();
    if (!cat4name) {
      cat4name = $("#cat4name").attr("placeholder");
    }
    let cat5name = $("#cat5name").val();
    if (!cat5name) {
      cat5name = $("#cat5name").attr("placeholder");
    }
    let cat6name = $("#cat6name").val();
    if (!cat6name) {
      cat6name = $("#cat6name").attr("placeholder");
    }
    let cat7name = $("#cat7name").val();
    if (!cat7name) {
      cat7name = $("#cat7name").attr("placeholder");
    }
    let cat8name = $("#cat8name").val();
    if (!cat8name) {
      cat8name = $("#cat8name").attr("placeholder");
    }
    let cat9name = $("#cat9name").val();
    if (!cat9name) {
      cat9name = $("#cat9name").attr("placeholder");
    }

    let updateDetails = {
      preferredName: preferredName,
      email: email,
      phone: phone,
      password: password,
      monthlyIncome: income,
      catNames: [
        cat0name,
        cat1name,
        cat2name,
        cat3name,
        cat4name,
        cat5name,
        cat6name,
        cat7name,
        cat8name,
        cat9name,
      ],
    };
    // console.log(updateDetails.catNames);
    // console.log("email: ", email)
    //$.put("/profile/1", updateDetails)
    $.ajax({
      url: "/profile/" + uid,
      method: "PUT",
      data: updateDetails,
    }).then(function (response) {
      if (response[0] === 1) {
        // alert("Zapisane aktualizacje.");
        location.replace("/" + uid);
      } else {
        // alert("Dane nie zostały zapisane.")
        // Otwórz model błędu
        $(".modal").modal();
        $("#error-body").empty();
        $("#error-body").append(
          `<p class="modal-p">Aktualizacja nie została zapisana.</p>`
        );
      }
    });
  });

  $(document).on("click", "#profileDelete", function (event) {
    event.preventDefault();
    let message = "Czy na pewno chcesz usunąć ten profil?";
    // deleteConfirm

    // otwórz modal błędu
    $(".modal").modal();
    $("#error-body").empty();
    $("#error-body").append(`<p class="modal-p">${message}</p>`);

    $(document).on("click", "#deleteConfirm", function (event) {
      event.preventDefault();
      console.log("clicked");
      // Wysła prośbę PUT z id.

      $.ajax({
        url: "/profile/delete/" + uid,
        method: "PUT",
      }).then(function (response) {
        //console.log("odpowiedź", response);
        if (response[0] === 1) {
          // Zaloguj się, jeśli pomyślnie usuń
          console.log("Profil deaktywowano.");
          // Wyślij użytkownika do zalogowania się
          location.replace("/login");
        } else {
          //alert("Profil nie został usunięty");
          $(".modal").modal();
          $("#error-body").empty();
          $("#error-body").append(
            `<p class="modal-p">Ten profil nie został usunięty.</p>`
          );
        }
      });
    });
  });

  $(".logOut").on("click", function () {
    deleteCookie();
    document.location.href = "/login";
  });
});
