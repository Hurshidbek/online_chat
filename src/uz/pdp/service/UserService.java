package uz.pdp.service;

import uz.pdp.model.User;
import uz.pdp.model.response.BaseResponse;
import uz.pdp.repository.UserInterface;

import java.util.List;
import java.util.UUID;

public class UserService extends UserInterface implements BaseResponse {

    public UserService() {
        User user = new User();
        user.setName("admin");
        user.setPhoneNumber("111111111");
        user.setPassword("root");
        user.setOwner(true);
        user.setActive(true);
        userList.add(user);
    }
    @Override
    public String add(User user) {
        if (isExistUser(user.getPhoneNumber()))
            return ERROR_USER_IS_EXIST;
        userList.add(user);
        return SUCCESS;
    }

    @Override
    public List<User> get() {
        return userList;
    }

    @Override
    public List<User> getById(UUID id) {
        return null;
    }

    @Override
    public String deleteById(UUID id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                userList.remove(user);
                return SUCCESS;
            }
        }
        return ERROR_USER_NOT_FOUND;
    }

    @Override
    public User login(String phoneNumber, String password) {
        for (User user : userList) {
            if (user.getPhoneNumber().equals(phoneNumber) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    @Override
    public String blockUser(UUID user_id) {
        int ind = 0;
        for (User user : userList) {
            if (user.getId().equals(user_id)) {
                user.setActive(!user.isActive());
                userList.set(ind, user);
                return SUCCESS;
            }
            ind++;
        }
        return ERROR_USER_NOT_FOUND;
    }

    @Override
    public String changePassword(UUID user_id, String newPassword) {
        int ind = 0;
        for (User user : userList) {
            if (user.getId().equals(user_id)) {
                user.setPassword(newPassword);
                userList.set(ind, user);
                return SUCCESS;
            }
            ind++;
        }
        return ERROR_USER_NOT_FOUND;
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        for (User user : userList) {
            if (user.getPhoneNumber().equals(phoneNumber))
                return user;
        }
        return null;
    }

    public boolean isExistUser(String phoneNumber) {
        for (User user : userList) {
            if (user.getPhoneNumber().equals(phoneNumber))
                return true;
        }
        return false;
    }
}
