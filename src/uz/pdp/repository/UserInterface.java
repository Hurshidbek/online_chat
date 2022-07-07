package uz.pdp.repository;

import uz.pdp.model.User;

import java.util.List;
import java.util.UUID;

public abstract class UserInterface implements BaseService<User, String, List<User>> {

    public abstract User login(String phoneNumber, String password);
    public abstract String blockUser(UUID user_id);
    public abstract String changePassword(UUID user_id, String newPassword);

}
