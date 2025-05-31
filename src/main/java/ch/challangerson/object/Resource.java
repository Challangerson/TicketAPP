package ch.challangerson.object;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Resource {

    private static final Map<Type, URL> resources = new HashMap<>();

    public Resource() {
//        this.resources.put(Type.MAIN, getClass().getResource("//ch.challangerson/view/Main.fxml"));
//        System.out.println(getClass().getResource("/ch/challangerson/view/Main.fxml"));
        resources.put(Type.MAIN, Resource.class.getResource("/ch/challangerson/view/Main.fxml"));
        resources.put(Type.LOGO, Resource.class.getResource("/ch/challangerson/view/image/parking-ticket.png"));
        resources.put(Type.ALERT_CSS, Resource.class.getResource("/ch/challangerson/style/alert.css"));
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
