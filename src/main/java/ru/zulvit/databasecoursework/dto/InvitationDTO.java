package ru.zulvit.databasecoursework.dto;

import jakarta.validation.constraints.NotEmpty;

public class InvitationDTO {
    private Integer invitationId;

    @NotEmpty
    private Integer eventId;

    @NotEmpty
    private Integer senderId;

    @NotEmpty
    private Integer recipientId;

    @NotEmpty
    private String time;

    @NotEmpty
    private Integer statusId;

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
