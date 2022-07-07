package uz.pdp.service;

import uz.pdp.model.Message;
import uz.pdp.model.User;
import uz.pdp.model.response.BaseResponse;
import uz.pdp.repository.MessageInterface;
import uz.pdp.repository.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessageService extends MessageInterface implements BaseResponse {

    @Override
    public String add(Message message) {
        messageList.add(message);
        return SUCCESS;
    }

    public List<Message> showUsersChatMessages(UUID fromUserId, UUID toUserId) {
        List<Message> chatMessages = new ArrayList<>();
        for (Message message : messageList) {
            if (message.getFromUserId().equals(fromUserId) && message.getToUserId().equals(toUserId))
                chatMessages.add(message);
        }
        return chatMessages;
    }

    @Override
    public List<Message> get() {
        return null;
    }

    @Override
    public List<Message> getById(UUID id) {
        return null;
    }

    @Override
    public String deleteById(UUID id) {
        return null;
    }

    public List<Message> getMessagesInGroup(UUID groupId) {
        List<Message> groupMessages = new ArrayList<>();
        for (Message message : messageList) {
            if (message.getToUserId().equals(groupId))
                groupMessages.add(message);
        }
        return groupMessages;
    }
}
