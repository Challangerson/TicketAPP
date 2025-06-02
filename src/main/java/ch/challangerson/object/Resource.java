package ch.challangerson.object;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Resource {

    private static final Map<Type, URL> resources = new HashMap<>();

    public Resource() {
        resources.put(Type.MAIN, Resource.class.getResource("/ch/challangerson/view/Main.fxml"));
        resources.put(Type.LOGO, Resource.class.getResource("/ch/challangerson/view/image/parking-ticket.png"));
        resources.put(Type.ALERT_CSS, Resource.class.getResource("/ch/challangerson/style/alert.css"));
        resources.put(Type.USER, Resource.class.getResource("/ch/challangerson/view/UserPanel.fxml"));
        resources.put(Type.ADD_PANEL, Resource.class.getResource("/ch/challangerson/view/AddSessionPanel.fxml"));
        resources.put(Type.STYLESHEET, Resource.class.getResource("/ch/challangerson/style/style.css"));
        resources.put(Type.REMOVE_PANEL, Resource.class.getResource("/ch/challangerson/view/RemoveSessionPanel.fxml"));
        resources.put(Type.LOGO, Resource.class.getResource("/ch/challangerson/view/image/logo.png"));
        resources.put(Type.ADMIN, Resource.class.getResource("/ch/challangerson/view/AdminPanel.fxml"));
        resources.put(Type.ADD_CAR, Resource.class.getResource("/ch/challangerson/view/AdminAddCar.fxml"));
        resources.put(Type.ADD_PUNISHMENT, Resource.class.getResource("/ch/challangerson/view/AdminAddPunishment.fxml"));
    }

    static {
//        resources.put(Type.MAIN, Resource.class.getResource("/ch/challangerson/view/Main.fxml"));
//        resources.put(Type.LOGIN, "/ch.challangerson/view/Login.fxml");
//        resources.put(Type.REGISTER, "/ch.challangerson/view/Register.fxml");
//        resources.put(Type.USER, "/ch.challangerson/view/User.fxml");
//        resources.put(Type.ADMIN, "/ch.challangerson/view/Admin.fxml");
    }


    public static URL getResourceName(Type type) {
        return resources.get(type);
    }



}
