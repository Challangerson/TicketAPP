package ch.challangerson.object.session;

import ch.challangerson.object.user.User;
import ch.challangerson.object.user.UserManager;
import ch.challangerson.sql.SQLExecute;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private final SQLExecute sqlExecute;


    public SessionManager(SQLExecute sqlExecute) {
        this.sqlExecute = sqlExecute;

    }

    public void addSession(User user, Session session) {
        user.getSessionList().add(session);

        this.sqlExecute.executeUpdateSync("INSERT INTO sesja (parking_id, start_parkowania, adres, user_id) " +
                "VALUES (" + session.getParking_id() + ", '" + session.getSession_start() + "', '" + session.getAddress() + "', " + user.getId() + ")");
    }

    public void removeSession(User user, Session session) {
        user.getSessionList().remove(session);

        this.sqlExecute.executeUpdateSync("DELETE FROM sesja WHERE parking_id = " + session.getParking_id() +
                " AND user_id = " + user.getId() + " AND adres = '" + session.getAddress() + "'");
    }

    public void loadSessionForUser(User user) {
        this.sqlExecute.executeQuery("SELECT s.* " +
                "FROM sesja s " +
                "JOIN uzytkownicy u ON s.user_id = u.id_uzytkownika " +
                "WHERE u.id_uzytkownika = " + user.getId(), resultSet -> {
            try {
                if (resultSet.next()) {
                    Session session = new Session(
                            resultSet.getInt("parking_id"),
                            true,
                            resultSet.getString("start_parkowania"),
                            resultSet.getString("adres"),
                            resultSet.getInt("user_id")
                    );

                    user.getSessionList().add(session);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
