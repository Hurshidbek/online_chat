package uz.pdp.model;

import java.util.UUID;

public class Message extends BaseModel{
    private UUID fromUserId;
    private UUID toUserId;
    private String messageContent;

    public Message() {

    }

    public UUID getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(UUID fromUserId) {
        this.fromUserId = fromUserId;
    }

    public UUID getToUserId() {
        return toUserId;
    }

    public void setToUserId(UUID toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    protected boolean checkName() {
        return super.name != null;
    }

    @Override
    public String toString() {
        return "Message: " +
                " title = " + name +
                " content = " + messageContent;
    }
}
