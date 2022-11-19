// Ten plik analizuje wszystkie zdarzenia Billflagged i odznacza je, gdy nadejdzie ich czas
var db = require("../models");
var moment = require("moment");
let today = moment().format("YYYY-MM-DD");

function dateBills(callback) {
  db.Event.findAll({
    where: {
      billFlag: true,
      // Do testów
      // billFlag: false,
      date: {
        [db.Op.lte]: today,
      },
    },
  }).then((x) => {
    updateObject = {
      billFlag: false,
      // Do testów
      // billFlag: true
    };

    // Do testów
    // reupdateObject = {
    //   // recurringFlag: true,
    //   billFlag: true
    // };

    x.forEach((v) => {
      // Jeśli płatność jest powtarzalna, najpierw utworzymy flagi w następnych miesiącach
      if (v.dataValues.recurringFlag) {
        // Data inspirowana (i przerobiona, aby dać koniec następnego miesiąca
        // stworzono przez silentw ze stack overflow: https://stackoverflow.com/questions/33440646/how-to-properly-add-1-month-from-now-to-current-date-in-moment-js
        moment.addRealMonth = function addRealMonth(d) {
          var fm = moment(d).add(1, "M");
          var fmEnd = moment(fm).endOf("month");
          return d.date() != fm.date() && fm.isSame(fmEnd.format("YYYY-MM-DD"))
            ? fm.endOf("month")
            : fm;
        };
        var nextMonth = moment.addRealMonth(moment()).format("YYYY-MM-DD");
        // koniec kodu

        newBillObject = {
          description: v.dataValues.description,
          category: v.dataValues.category,
          amount: v.dataValues.amount,
          billFlag: v.dataValues.billFlag,
          recurringFlag: v.dataValues.recurringFlag,
          periodicity: v.dataValues.periodicity,
          activeFlag: v.dataValues.activeFlag,
          UserId: v.dataValues.UserId,
          date: nextMonth,
        };
        db.Event.create(newBillObject).then((response) => {
          console.log("dodano flagę powtarzalną");
        });
      }
      // Następnie aktualizujemy flagę rachunków, aby wskazać, że dzisiaj została zapłacona
      db.Event.update(updateObject, {
        where: {
          id: v.dataValues.id,
        },
      }).then((y) => {
        console.log("Zaktualizowano billFlag", y);
        // wyłączyć callbackdo testów
        callback();
      });

      // do testów
      // db.Event.update(reupdateObject, {
      //   where: {
      //     id: v.dataValues.id
      //   }
      // }).then(y => {
      //   // console.log("zaktualizowano flagę powtarzalną", y);
      //   // wyłączyć callback do testów
      //   // callback();
      // });
    });
  });
}

// do testów
// dateBills();

module.exports = dateBills;
