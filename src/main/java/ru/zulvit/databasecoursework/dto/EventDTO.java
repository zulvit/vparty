package ru.zulvit.databasecoursework.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class Event {
    @NotBlank(message = "название не может быть пустым")
    @Size(max = 50, min = 4, message = "название должно быть от 4 до 50 символов")
    private String title;

    @NotBlank(message = "описание не может быть пустым")
    @Size(max = 200, message = "описание не должно превышать 200 символов")
    private String description;

    @NotBlank(message = "местоположение не может быть пустым")
    @Size(max = 100, message = "местоположение не должно превышать 100 символов")
    private String location;

    @NotBlank(message = "время начала не может быть пустым")
    private String startTime;

    @NotBlank(message = "время окончания не может быть пустым")
    private String endTime;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
