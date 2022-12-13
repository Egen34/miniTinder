package kg.megacom.miniTinder.models;

public class Orders {
    private Long id;
    private Users senderId;
    private Users recipientId;
    private boolean match;


    public Orders() {
    }

    public Orders(Users senderId, Users recipientId, boolean match) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.match = match;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getSenderId() {
        return senderId;
    }

    public void setSenderId(Users senderId) {
        this.senderId = senderId;
    }

    public Users getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Users recipientId) {
        this.recipientId = recipientId;
    }

    public boolean isMatch() {
        return match;
    }

    public void setMatch(boolean match) {
        this.match = match;
    }


}
