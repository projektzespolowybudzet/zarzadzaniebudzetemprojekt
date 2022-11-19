// Tworzy plik dziennika do aktualizacji, gdy zwykłe zadania były ostatnie uruchomione,
// za każdym razem, gdy serwer pracuje, sprawdzamy, czy interwał był zbyt długi
// użyj tego, aby uruchomić odpowiednie pliki w razie potrzeby
let fs = require("fs");
let moment = require("moment");
var path = require("path");
var logPath = path.join(__dirname, "./chronLog.txt");

function checkLog(callback) {
  fs.readFile(`${logPath}`, "utf8", (err, data) => {
    if (err) throw err;
    data = data.split("\n");
    callback(data[data.length - 2]);
  });
}
let logDate = moment().format("YYYY-MM-DD hh:mm:ss");
function execute(callback) {
  checkLog((x) => {
    let lastLog = x.substr(0, 10);
    let now = logDate.substr(0, 10);
    if (now != lastLog) {
      console.log("gotowe chronJobs pracuje");
      writeLog(logDate);
      callback();
    }
  });
}

function writeLog(logDate) {
  fs.appendFile(`${logPath}`, `${logDate} \n`, (err) => {
    if (err) throw err;
    console.log('"dane do dodania" dołączono do pliku');
  });
}

module.exports = execute;
