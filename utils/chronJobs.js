// Tutaj uruchamiamy wszystkie wymagane pliki, gdy chronJobScheduler działa (za każdym razem, gdy serwer budzi się po raz pierwszy tego dnia)
var dateBills = require("./dateBills");
var mail = require("./mail");
function chronJobs() {
  dateBills(otherfunction);
  mail();
  console.log("dateBills updated");
}
function otherfunction() {
  console.log("gotowe");
}
module.exports = chronJobs;
