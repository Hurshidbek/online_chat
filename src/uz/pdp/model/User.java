package uz.pdp.model;

public class User extends BaseModel{
    private String phoneNumber;
    private String password;
    private boolean isActive;
    private boolean isOwner;

    public User() {

    }

    public User(String name, String phoneNumber, String password, boolean isActive, boolean isOwner) {
        super(name);
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isActive = isActive;
        this.isOwner = isOwner;
    }

    public User(String phoneNumber, String password, boolean isActive, boolean isOwner) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isActive = isActive;
        this.isOwner = isOwner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    @Override
    protected boolean checkName() {
        return super.name != null;
    }

    @Override
    public String toString() {
        return "User: " +
                " id= " + id +
                " name= " + name +
                " phoneNumber= " + phoneNumber +
                " password= " + password +
                " isActive= " + isActive +
                " isOwner= " + isOwner;
    }
}
