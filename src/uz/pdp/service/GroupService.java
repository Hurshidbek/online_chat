package uz.pdp.service;

import uz.pdp.model.Group;
import uz.pdp.model.Message;
import uz.pdp.model.User;
import uz.pdp.model.response.BaseResponse;
import uz.pdp.repository.GroupInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupService extends GroupInterface implements BaseResponse {
    @Override
    public String add(Group group) {
        if (isExistGroup(group.getName()))
            return ERROR_GROUP_NAME_EXIST;
        groupList.add(group);
        return SUCCESS;
    }

    @Override
    public List<Group> get() {
        return null;
    }

    @Override
    public List<Group> getById(UUID id) {
        return null;
    }

    @Override
    public String deleteById(UUID id) {
        return null;
    }

    public Group getGroup(String name) {
        for (Group group : groupList) {
            if (group.getName().equals(name))
                return group;
        }
        return null;
    }

    public boolean checkUserInGroup(Group group, User user) {
        for (User user1 : group.getUsers()) {
            if (user1.getName().equals(user.getName()))
                return true;
        }
        return false;
    }

    public String addUserToGroup(Group group, User user) {
        if (!checkUserInGroup(group, user)) {
            group.getUsers().add(user);
            return SUCCESS;
        }
        else return ERROR_USER_IN_GROUP;
    }

    public boolean isExistGroup(String name) {
        for (Group group : groupList) {
            if (group.getName().equals(name))
                return true;
        }
        return false;
    }

    public List<User> getGroupUsers(Group group) {
        return group.getUsers();
    }
}
