package domain;

import services.githubEvents.githubEvents.ServiceGitHubEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class GitHubEventProcessor {

  public static void main(String[] args) throws IOException {
    ServiceGitHubEvent serviceGitHubEvent = ServiceGitHubEvent.getInstance();
    GitHubEventAnalyzer eventsAnalyzer = new GitHubEventAnalyzer(serviceGitHubEvent);

    System.out.println("\n--------------------------------------------------------");
    System.out.println("Type the github username which you'd like to see events:");
    Scanner scanner = new Scanner(System.in);
    String username = scanner.nextLine();
    while(!Objects.equals(username, "!")){
      eventsAnalyzer.analyzeUserEvents(username);

      System.out.println("\n--------------------------------------------------------");
      System.out.println("Type the github username which you'd like to see events (\"!\" to finish):");
      scanner = new Scanner(System.in);
      username = scanner.nextLine();
    }
    scanner.close();
    System.out.println("Thank you for using GitHub Event Analyzer!");
  }
}
