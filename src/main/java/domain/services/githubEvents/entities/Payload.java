package domain.services.githubEvents.entities;

import lombok.Getter;

@Getter
public class Payload {
  private String action;
  private String push_id;
  private String size;
  private String distinct_size;
  private String ref;
  private String head;
  private String before;
}
