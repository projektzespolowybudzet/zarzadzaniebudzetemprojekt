// dependencies
var express = require("express");
var exphbs = require("express-handlebars");
var app = express();
var PORT = process.env.PORT || 8080;
var chronJobScheduler = require("./utils/chron");
require("dotenv").config();

var chronJobs = require("./utils/chronJobs");
var dateBills = require("./utils/dateBills");
// console.log(chronJobScheduler(chronJobs));

// console.log(chronJobs);
// baza danych wymaga wszystkich modeli w folderze
var db = require("./models");

// Middleware
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// Static Serve
app.use(express.static("./public"));

// Ustaw Handlebars jako domyślny silnik szablonów.
app.engine("handlebars", exphbs({ defaultLayout: "main" }));
app.set("view engine", "handlebars");

// Routes
// e.g. require("./routes/html-routes.js")(app);
require("./controllers/Controller.js")(app);
// Ważne synchronizacja bazy danych
db.sequelize.sync().then(function () {
  // console.log("hello", chronJobs);

  chronJobScheduler(chronJobs);
  app.listen(PORT, function () {
    console.log("Aplikacja nasłuchuje PORT " + PORT);
  });
});
