module.exports = function (sequelize, DataTypes) {
  var Event = sequelize.define("Event", {
    // Jak aplikacja zwróci się do użytkownika
    description: {
      type: DataTypes.STRING,
      //   allowNull: false,
      validate: {
        len: [1],
      },
    },
    category: {
      type: DataTypes.INTEGER(2),
      allowNull: false,
      validate: {
        len: [1],
      },
    },
    amount: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 1000,
    },
    // Opcjonalne pole wejścia na przyszłe lub przeszłe wydarzenia, w przeciwnym razie weźmie CREATED AT
    date: {
      type: DataTypes.DATEONLY,
      allowNull: false,
    },
    // is bill(true oznacza brak wydarzenia; brak transferów)
    billFlag: {
      type: DataTypes.BOOLEAN,
      defaultValue: false,
    },
    // dla opłat powtarzalnych
    recurringFlag: {
      type: DataTypes.BOOLEAN,
      defaultValue: false,
    },
    //** okres powtzarzalności
    periodicity: {
      type: DataTypes.STRING,
      defaultValue: "Misięcznie",
    },

    // Jeśli użytkownik chce anulować zdarzenie, zostanie ono dezaktywowane
    activeFlag: {
      type: DataTypes.BOOLEAN,
      defaultValue: true,
    },
  });
  // zdarzenia 1 to many
  Event.associate = function (models) {
    Event.belongsTo(models.User, {
      foreignKey: {
        allowNull: false,
      },
    });
  };

  return Event;
};
