package ch.challangerson.object.parking;

import ch.challangerson.sql.SQLExecute;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingManager {

    private final Map<String, Parking> parkings;
    private final SQLExecute sqlExecute;

    public ParkingManager(SQLExecute sqlExecute) {
        this.sqlExecute = sqlExecute;
        this.parkings = new HashMap<>();

        this.loadParkings();
    }

    public void loadParkings() {
        this.sqlExecute.executeQuery("SELECT * FROM parkingi;", resultSet -> {
            try {
                while (resultSet.next()) {
                    Parking parking = new Parking(
                            resultSet.getInt("id_parkingu"),
                            resultSet.getInt("ilosc_miejsc"),
                            resultSet.getInt("zajete_miejsca"),
                            resultSet.getBoolean("platny"),
                            resultSet.getBoolean("karta"),
                            resultSet.getDouble("cena_per_h"),
                            resultSet.getString("adress")
                    );

                    parkings.put(resultSet.getString("adress"), parking);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Parking getParking(String adress) {
        return parkings.get(adress);
    }

    public Map<String, Parking> getParkings() {
        return this.parkings;
    }
}
