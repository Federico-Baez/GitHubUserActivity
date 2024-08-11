package domain.services.githubEvents.entities;

import lombok.Getter;

@Getter
public class Actor {
  private String id;
  private String login;
  private String display_login;
  private String gravatar_id;
  private String url;
  private String avatar_url;
}
