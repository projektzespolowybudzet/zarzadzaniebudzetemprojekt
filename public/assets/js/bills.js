$(document).ready(function () {
  var uid = checkCookie();
  if (!uid) {
    document.location.href = "/login";
  }

  $(".dashboardLink").attr("href", `/${uid}`);
  $(".profileLink").attr("href", `/profile/${uid}`);

  $(".buttonDelete").on("click", function () {
    var id = $(this).attr("data-billId");
    // console.log(id);
    //tworzy ajax put
    $.ajax(`/bills/delete/${id}`, {
      type: "PUT",
    }).then(function (data) {
      // console.log(data);
      if (data[0] === 1) {
        // console.log('usunięto wydatek ' + id);
        location.reload();
      }
    });
  });

  $(".logOut").on("click", function () {
    deleteCookie();
    document.location.href = "/login";
  });
});
