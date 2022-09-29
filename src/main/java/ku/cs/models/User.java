package ku.cs.models;

import javafx.scene.image.Image;

public class User implements Comparable {
    public String name;
    public String username;
    public String password;
    public String agency;
    private String isAdmin;
    private String isBanned;
    private Image image;
    public User(String name, String username, String password, String agency, String isAdmin, String isBanned) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.agency = agency;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
    }
    public Image getImage() {
        return image;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(String isBanned) {
        this.isBanned = isBanned;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
