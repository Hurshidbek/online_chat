package uz.pdp.repository;

import uz.pdp.model.Group;
import uz.pdp.model.Message;
import uz.pdp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface BaseService<T, R, E> {

    List<User> userList = new ArrayList<>();
    List<Message> messageList = new ArrayList<>();
    List<Group> groupList = new ArrayList<>();

    R add(T t);
    E get();
    E getById(UUID id);
    R deleteById(UUID id);
}
