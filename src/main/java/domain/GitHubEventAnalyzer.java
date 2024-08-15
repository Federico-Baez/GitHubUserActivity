package domain;

import exceptions.ApiException;
import services.githubEvents.githubEvents.ServiceGitHubEvent;
import java.io.IOException;
import java.util.List;

public class GitHubEventAnalyzer {
  private final ServiceGitHubEvent serviceGitHubEvent;

  public GitHubEventAnalyzer(ServiceGitHubEvent service) {
    this.serviceGitHubEvent = service;
  }

  public void analyzeUserEvents(String username) throws IOException {
    try{
      List<Repository> repositories = serviceGitHubEvent.getRepositories(username);
      if (!repositories.isEmpty()) {
        System.out.println("\n" + username + " Repositories Activity");
        repositories.forEach(repository -> {
          System.out.println("\n" + repository.getName());
          System.out.println("\t -stars: " + repository.getStars());
          System.out.println("\t -branches: " + repository.getBranches());
          System.out.println("\t -commits: " + repository.getCommits());
          System.out.println("\t -opened issues: " + repository.getOpenedIssues());
          System.out.println("\t -closed issues: " + repository.getClosedIssues());
          System.out.println("\t -opened pull requests: " + repository.getOpenedPullRequests());
          System.out.println("\t -closed pull requests: " + repository.getClosedPullRequests());
        });
    }
    } catch (IOException e) {
      System.out.println("Unexpected error occurred. Please try again later.");
    } catch (ApiException e) {
      System.out.println(e.getMessage());
    }
    }
}
