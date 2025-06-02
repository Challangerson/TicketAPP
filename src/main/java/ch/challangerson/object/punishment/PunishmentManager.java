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

    public void deletePunishment(int id) {
        this.sqlExecute.executeUpdateAsync("DELETE FROM kary WHERE id_kary = '" + id + "';");
    }

    public void addPunishment(User user, Punishment punishment) {
        this.sqlExecute.executeUpdateAsync("INSERT INTO kary (id_uzytkownika, cena, zazalenia, powod, oplacone, recydywa) " +
                "VALUES ('" + user.getId() + "', '" + punishment.getPrice() + "', '" +
                punishment.getTicket() + "', '" + punishment.getResponse() + "', '" + (punishment.isPaid() ? 1 : 0) + "', '" +
                (punishment.isRecidivism() ? 1 : 0) + "');");

        user.getPunishmentList().add(punishment);
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
