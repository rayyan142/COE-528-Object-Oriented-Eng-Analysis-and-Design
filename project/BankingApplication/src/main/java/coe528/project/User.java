package coe528.project;

public abstract class User {
    
    protected String username;
    protected String password;
    
    public User(String username, String password) {
        if(username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Username and password must be non-empty.");
        }
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}
