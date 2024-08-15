package services.githubEvents.githubEvents.entities;

import lombok.Getter;

@Getter
public class Event {
  private String id;
  private String type;
  private Actor actor;
  private Repo repo;
  private Payload payload;
  private Boolean isPublic;
  private String createdAt;
}
