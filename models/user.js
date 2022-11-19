module.exports = function (sequelize, DataTypes) {
  var User = sequelize.define("User", {
    // Jak aplikacja zwróci się do użytkownika
    preferredName: {
      type: DataTypes.STRING,
      allowNull: false,
      validate: {
        len: [1],
      },
    },
    password: {
      type: DataTypes.STRING,
      allowNull: false,
      validate: {
        len: [1],
      },
    },
    monthlyIncome: {
      type: DataTypes.DECIMAL(12, 2),
      defaultValue: 1000,
    },
    // E -mail jest wymagany, ponieważ jest używany do logowania
    email: {
      type: DataTypes.STRING,
      allowNull: false,
      validate: { len: [1] },
    },
    // Jeśli użytkownik chciałby aktualizacji e -mail
    emailFlag: {
      type: DataTypes.BOOLEAN,
      defaultValue: false,
    },
    phone: {
      type: DataTypes.STRING,
      //   allowNull: false,
      // validate: { len: [10] }
    },
    //Jeśli użytkownik chciałby aktualizacji tekstu
    phoneFlag: {
      type: DataTypes.BOOLEAN,
      defaultValue: false,
    },
    // Użytkownik może nazwać własne kategorie budżetu
    cat0name: {
      type: DataTypes.STRING,
      defaultValue: "Wydatki",
      validate: { len: [1] },
    },
    cat0cap: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 1000,
    },
    cat0warn: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 800,
    },
    // Pozostałe kategorie są opcjonalne, do wykresu kołowego (wykres)
    cat1name: DataTypes.STRING,
    cat1cap: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat1warn: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat2warn: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat2name: DataTypes.STRING,
    cat2cap: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat3name: DataTypes.STRING,
    cat3cap: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat3warn: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat4name: DataTypes.STRING,
    cat4cap: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat4warn: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat5name: DataTypes.STRING,
    cat5cap: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat5warn: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat6name: DataTypes.STRING,
    cat6cap: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat6warn: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat7name: DataTypes.STRING,
    cat7cap: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat7warn: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat8name: DataTypes.STRING,
    cat8cap: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat8warn: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat9name: DataTypes.STRING,
    cat9cap: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    cat9warn: {
      type: DataTypes.DECIMAL(10, 2),
      defaultValue: 0,
    },
    // Jeśli użytkownik chce „usunąć” swoje konto, zostanie ono dezaktywowane
    activeFlag: {
      type: DataTypes.BOOLEAN,
      defaultValue: true,
    },
  });
  // zdarzenie 1 user to many
  User.associate = function (models) {
    // console.log("hello", models);

    User.hasMany(models.Event, {
      // onDelete: "cascade"
    });
  };

  return User;
};
