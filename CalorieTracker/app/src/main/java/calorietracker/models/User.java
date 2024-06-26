package calorietracker.models;

public class User extends Model {
    private String username, password;

    public User(int id, String username) {
        super(id);
        this.username = username;
    }

    public User(int id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        
}