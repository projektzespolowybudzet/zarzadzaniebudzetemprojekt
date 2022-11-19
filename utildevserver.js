// dependencies
var express = require("express");
var exphbs = require("express-handlebars");
var app = express();
var PORT = process.env.PORT || 8080;
// baza danych wymaga wszystkich modeli w folderze
var db = require("./models");
require("dotenv").config();

// Middleware
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// Static Serve
app.use(express.static("public"));

// Ustaw Handlebars jako domyślny silnik szablonów.
app.engine("handlebars", exphbs({ defaultLayout: "main" }));
app.set("view engine", "handlebars");

// Routes
// e.g. require("./routes/html-routes.js")(app);
// require("./controllers/Controller.js")(app);

// //dev environment <<--Tutaj pracujemy
// //KOMENTOWANE WARTOŚCITESTOWE

var db = require("./models");
var thismonth = require("./utils/date");
const nodemailer = require("nodemailer");
// let transporter = nodemailer.createTransport(options[ defaults])
// findRecipients();
function findRecipients() {
  db.User.findAll({
    where: {
      emailFlag: 0,
      // 1 daje możliwość wyłączenia wysylania wiadomości (brak obsługi)
      // emailFlag:1
    },
  }).then((x) => {
    // console.log(x);
    // sprawdzenie czy nie przekroczono budżetu
    x.forEach((x, i) => {
      let name = x.dataValues.preferredName;
      let email = x.dataValues.email;
      let uid = x.dataValues.id;
      let flags = {};
      let catNames = [
        {
          cat: x.dataValues.cat0name,
        },
        {
          cat: x.dataValues.cat1name,
        },
        {
          cat: x.dataValues.cat2name,
        },
        {
          cat: x.dataValues.cat3name,
        },
        {
          cat: x.dataValues.cat4name,
        },
        {
          cat: x.dataValues.cat5name,
        },
        {
          cat: x.dataValues.cat6name,
        },
        {
          cat: x.dataValues.cat7name,
        },
        {
          cat: x.dataValues.cat8name,
        },
        {
          cat: x.dataValues.cat9name,
        },
      ];
      let catCaps = [
        {
          catCap: x.dataValues.cat0cap,
        },
        {
          catCap: x.dataValues.cat1cap,
        },
        {
          catCap: x.dataValues.cat2cap,
        },
        {
          catCap: x.dataValues.cat3cap,
        },
        {
          catCap: x.dataValues.cat4cap,
        },
        {
          catCap: x.dataValues.cat5cap,
        },
        {
          catCap: x.dataValues.cat6cap,
        },
        {
          catCap: x.dataValues.cat7cap,
        },
        {
          catCap: x.dataValues.cat8cap,
        },
        {
          catCap: x.dataValues.cat9cap,
        },
      ];
      let catWarns = [
        {
          catWarn: x.dataValues.cat0warn,
        },
        {
          catWarn: x.dataValues.cat1warn,
        },
        {
          catWarn: x.dataValues.cat2warn,
        },
        {
          catWarn: x.dataValues.cat3warn,
        },
        {
          catWarn: x.dataValues.cat4warn,
        },
        {
          catWarn: x.dataValues.cat5warn,
        },
        {
          catWarn: x.dataValues.cat6warn,
        },
        {
          catWarn: x.dataValues.cat7warn,
        },
        {
          catWarn: x.dataValues.cat8warn,
        },
        {
          catWarn: x.dataValues.cat9warn,
        },
      ];
      // zdarzenia z misiaca
      db.Event.findAll({
        where: {
          userId: uid,
          activeFlag: true,
          date: thismonth,
        },
      }).then((y) => {
        // // inicjalizacja tabel
        let catTotalFloats = [
          {
            catTotalF: 0,
          },
          {
            catTotalF: 0,
          },
          {
            catTotalF: 0,
          },
          {
            catTotalF: 0,
          },
          {
            catTotalF: 0,
          },
          {
            catTotalF: 0,
          },
          {
            catTotalF: 0,
          },
          {
            catTotalF: 0,
          },
          {
            catTotalF: 0,
          },
          {
            catTotalF: 0,
          },
        ];
        let catCapFloats = [
          {
            catCapF: 0,
          },
          {
            catCapF: 0,
          },
          {
            catCapF: 0,
          },
          {
            catCapF: 0,
          },
          {
            catCapF: 0,
          },
          {
            catCapF: 0,
          },
          {
            catCapF: 0,
          },
          {
            catCapF: 0,
          },
          {
            catCapF: 0,
          },
          {
            catCapF: 0,
          },
        ];
        let catWarnFloats = [
          {
            catWarnF: 0,
          },
          {
            catWarnF: 0,
          },
          {
            catWarnF: 0,
          },
          {
            catWarnF: 0,
          },
          {
            catWarnF: 0,
          },
          {
            catWarnF: 0,
          },
          {
            catWarnF: 0,
          },
          {
            catWarnF: 0,
          },
          {
            catWarnF: 0,
          },
          {
            catWarnF: 0,
          },
        ];

        // teraz przejdź przez wszystkie zdarzenia i dodaj każdą kwotę zdarzenia do odpowiedniej tablicy całkowitej
        // pozycja przy użyciu identyfikatora kategorii jako indeksu
        y.forEach((v) => {
          let w = v.dataValues;
          catTotalFloats[w.category].catTotalF += parseFloat(w.amount);
        });
        // teraz, gdy wszystkie zdarzenia są posortowane, wykonaj obliczenia na kategoriach, aby znaleźć wartości procentowe
        catTotalFloats.forEach((v, i) => {
          catCapFloats[i].catCapF = parseInt(
            (v.catTotalF * 100) / parseFloat(catCaps[i].catCap)
          );
          catWarnFloats[i].catWarnF = parseInt(
            (v.catTotalF * 100) / parseFloat(catWarns[i].catWarn)
          );
          // Obsługa NaN
          if (isNaN(catCapFloats[i].catCapF)) {
            catCapFloats[i].catCapF = 0;
          }
          if (isNaN(catWarnFloats[i].catWarnF)) {
            catWarnFloats[i].catWarnF = 0;
          }
        });
        // oznaczanie kategorii z przekroczonym budżetem
        catCapFloats.forEach((v, i) => {
          if (v.catCapF > 100) {
            flags[`C${i}`] = v.catCapF;
          }
        });
        catWarnFloats.forEach((v, i) => {
          if (v.catWarnF > 100) {
            flags[`W${i}`] = v.catWarnF;
          }
        });
        let a = {
          flags,
          name,
          email,
          catNames,
          catCaps,
          catWarns,
          catTotalFloats,
          catCapFloats,
          catWarnFloats,
        };
        // console.log(a.flags, a.name, a.email, a.catNames);
        let flagArr = Object.keys(a.flags);
        let messageStart = `<h4>Cześć ${a.name}!</h4>`;
        let message = `<p> Złe wieści: </p>`;
        // komunikat na podstawie flag
        flagArr.forEach((v, i) => {
          let b = v.split("");
          if (b[0] == "C") {
            let messagePlus = `<p>Jesteś ${
              a.flags[flagArr[i]]
            }% powyżej budżetu dla ${catNames[b[1]].cat}.</p>`;
            message = message.concat(messagePlus);
          } else if (b[0] == "W") {
            let messagePlus = `<p>Jesteś ${
              a.flags[flagArr[i]]
            }% powyżej wartości ostrzegawczej dla ${catNames[b[1]].cat}.</p>`;
            message = message.concat(messagePlus);
          }
        });
        let messageEnd =
          " <p> Próbowałeś</p><p>Pozdrawiamy,</p> <p><i>Dolarek Team</i></p>";
        // nic nie wysyłamy jeżeli nic nie przekroczono
        if (flagArr.length > 0) {
          //Do sprawdzenia działanie na realnym serwerze
          async function sendThatMail() {
            let transporter = nodemailer.createTransport({
              service: "gmail",
              auth: {
                user: process.env.GMAILUSER,
                pass: process.env.GMAILPASS,
              },
            });
            var mailOptions = {
              from: "zarzadzaniebudzetemprojekt@gmail.com",
              to: `doonaj@gmail.com`,
              // wstawmy prawdziwy email, kiedy skończymy testować - miejmy nadzieję, że w heroku/jaws nie są one wszystkie fałszywe i zadziała
              // to: `${a.email}`,
              subject: "Test",
              text: `Cześć ${a.name}`,
              html: `${messageStart}${message}${messageEnd}`,
            };
            transporter.sendMail(mailOptions, (err, info) => {
              if (err) {
                throw new Error("O nie, jakiś błąd");
              } else {
                console.log(info);
              }
            });
          }
          sendThatMail().catch(console.error);
        }
      });
    });
  });
}

// // musimy pobrać informacje z bazy danych i utworzyć tutaj właściwą treść maila
// var name;
// var email;
// var message;

// // ta część wyśle post
// sendEmail (name, email, message) {
//     fetch('/send', {
//       method: 'POST',
//       headers: {
//         'Accept': 'application/json',
//         'Content-Type': 'application/json'
//       },
//       body: JSON.stringify({
//         name: name,
//         email: email,
//         message: message
//       })
//     })
//     .then((res) => res.json())
//     .then((res) => {
//       console.log('odpowiedź: ', res);
//     })
//     .catch((err) => {
//       console.error('błąd: ', err);
//     })
//    }

// //    Myślę, że musimy tego wymagać w aplikacji na serwerze

// // transporter będzie faktycznie wysyłał maile z ustawionej przez nas usługi
// app.post('/send', function(req, res, next) {
//     const transporter = nodemailer.createTransport({
//       service: 'gmail',
//       auth: {
//         user: 'test-email@gmail.com',
//         pass: 'test123'
//       }
//     })
//   }
//     const mailOptions = {
//       from: `${req.body.email}`,
//       to: 'test-email@gmail.com',
//       subject: `${req.body.name}`,
//       text: `${req.body.message}`,
//       replyTo: `${req.body.email}`
//     }
//     transporter.sendMail(mailOptions, function(err, res) {
//         if (err) {
//           console.error('there was an error: ', err);
//         } else {
//           console.log('here is the res: ', res)
//         }
//       })

// Route 15
// var id = 1;
// // Będziemy potrzebować głównej strony pulpitu nawigacyjnego, aby przesłać przez id. To prawdopodobnie będzie w lokalnym magazynie.
// var queryObject = {
//   uid: id
// };

// var get15 = require("./utils/get15.js");
// get15(queryObject, function(res) {
//   console.log("server", res);
// });

// Ważne synchronizacja bazy danych
db.sequelize.sync().then(function () {
  app.listen(PORT, function () {
    // console.log("Aplikacja nasłuchuje PORT " + PORT);
  });
});
