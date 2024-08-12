package domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Repository {
  private String owner;
  private String name;
  private int stars;
  private int branches;
  private int commits;
  private int openedIssues;
  private int closedIssues;
  private int openedPullRequests;
  private int closedPullRequests;


  public Repository(String owner, String name) {
    this.owner = owner;
    this.name = name;
  }
}
