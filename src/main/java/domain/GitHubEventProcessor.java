package domain;

import domain.services.githubEvents.ServiceGitHubEvent;
import domain.services.githubEvents.entities.Event;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GitHubEventProcessor {

  public static void main(String[] args) throws IOException {
    ServiceGitHubEvent serviceGitHubEvent = ServiceGitHubEvent.getInstance();
    GitHubEventAnalyzer eventsAnalyzer = new GitHubEventAnalyzer(serviceGitHubEvent);

    System.out.println("\n--------------------------------------------------------");
    System.out.println("Type the github username which you'd like to see events:");
    Scanner scanner = new Scanner(System.in);
    String username = scanner.nextLine();

    eventsAnalyzer.analyzeUserEvents(username);
  }
}
