package ch.challangerson.object.punishment;

import ch.challangerson.object.user.User;
import ch.challangerson.object.user.UserManager;
import ch.challangerson.sql.SQLExecute;

import java.sql.SQLException;

public class PunishmentManager {
    private final SQLExecute sqlExecute;
    private final UserManager userManager;

    public PunishmentManager(SQLExecute sqlExecute, UserManager userManager) {
        this.sqlExecute = sqlExecute;
        this.userManager = userManager;

        this.userManager.getUsers().values().forEach(this::loadPunishments);
    }

    private void loadPunishments(User user) {
        this.sqlExecute.executeQuery("SELECT * FROM kary where id_uzytkownika ='" + user.getId() + "';", resultSet -> {
            try {
                while (resultSet.next()) {
                    Punishment punishment = new Punishment(
                            resultSet.getInt("id_kary"),
                            resultSet.getInt("id_uzytkownika"),
                            resultSet.getInt("cena"),
                            resultSet.getInt("zazalenia"),
                            resultSet.getString("powod"),
                            resultSet.getBoolean("oplacone"),
                            resultSet.getBoolean("recydywa")
                        );

                    user.getPunishmentList().add(punishment);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
