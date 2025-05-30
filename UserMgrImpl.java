import java.util.List;

public class UserMgrImpl implements IUserMgr {
    List<User> users;

    public UserMgrImpl(List<User> users) {
        this.users = users;
    }

    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        for (User u : users) {
            if (u.username.equals(username) && u.password.equals(password)) {
                return u;
            }
        }
        return null;
    }
}