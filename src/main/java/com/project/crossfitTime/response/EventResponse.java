package com.project.crossfitTime.response;

import com.project.crossfitTime.domain.Event;

public class EventResponse {

    private Integer id;
    private String eventName;
    private String eventContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public static EventResponse from(Event event) {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEventContent(event.getEventContent());
        eventResponse.setEventName(event.getEventName());
        eventResponse.setId(event.getId());
        return eventResponse;
    }
}
