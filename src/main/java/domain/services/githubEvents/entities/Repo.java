package domain.services.githubEvents.entities;

import lombok.Getter;

@Getter
public class Repo {
  private String id;
  private String name;
  private String url;
}
