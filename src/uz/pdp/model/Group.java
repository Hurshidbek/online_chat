package uz.pdp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group extends BaseModel{
    private UUID adminId;
    private List<User> users = new ArrayList<>();

    public Group() {

    }

    public Group(String name, UUID adminId, List<User> users) {
        super(name);
        this.adminId = adminId;
        this.users = users;
    }

    public Group(UUID adminId, List<User> users) {
        this.adminId = adminId;
        this.users = users;
    }

    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    protected boolean checkName() {
        return super.name != null;
    }
}
