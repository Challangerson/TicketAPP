package ch.challangerson.object.car;

import ch.challangerson.object.user.User;
import ch.challangerson.object.user.UserManager;
import ch.challangerson.sql.SQLExecute;

import java.sql.SQLException;

public class CarManager {

    private final SQLExecute sqlExecute;

    public CarManager(SQLExecute sqlExecute, UserManager userManager) {
        this.sqlExecute = sqlExecute;

        userManager.getUsers().values().forEach(this::setCars);

    }

    public void deleteCar(int id) {
        System.out.println("Deleting car with id: " + id);
        this.sqlExecute.executeUpdateSync("DELETE FROM pojazdy WHERE id_pojazdu = '" + id + "';");

    }

    public void addCar(User user, Car car) {
        this.sqlExecute.executeUpdateSync("INSERT INTO pojazdy (id_uzytkownika, rok_produkcji, vin, marka, model, nr_rejestracyjny, ubezpieczony) " +
                "VALUES ('" + user.getId() + "', '" + car.getYear() + "', '" + car.getVin() + "', '" + car.getBrand() + "', '" + car.getModel() + "', '" + car.getRegistration() + "', '" + (car.isUbezpieczony() ? 1 : 0) + "');");
        user.getCars().add(car);
    }

    public void setCars(User user) {
        this.sqlExecute.executeQuery("SELECT * FROM pojazdy WHERE id_uzytkownika ='" + user.getId() + "';", resultSet -> {
            try {
                if(resultSet.next()) {
                    Car car = new Car(
                            resultSet.getInt("id_pojazdu"),
                            resultSet.getInt("id_uzytkownika"),
                            resultSet.getInt("rok_produkcji"),
                            resultSet.getString("vin"),
                            resultSet.getString("marka"),
                            resultSet.getString("model"),
                            resultSet.getString("nr_rejestracyjny"),
                            resultSet.getBoolean("ubezpieczony")
                    );

                    user.getCars().add(car);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public SQLExecute getSqlExecute() {
        return this.sqlExecute;
    }
}
