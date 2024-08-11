package domain;

import domain.services.githubEvents.ServiceGitHubEvent;
import domain.services.githubEvents.entities.Event;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GitHubEventProcessor {

  public static void main(String[] args) throws IOException {
    ServiceGitHubEvent serviceGitHubEvent = ServiceGitHubEvent.getInstance();
    System.out.println("\n--------------------------------------------------------");
    System.out.println("Type the github username which you'd like to see events:");
    Scanner scanner = new Scanner(System.in);
    String username = scanner.nextLine();

    Event[] results = serviceGitHubEvent.getUserEventList(username);

    System.out.println("\nevents amount: "+results.length);
    for (Event event : results) {
      //pushes
      if (event.getType().equals("PushEvent"))
      System.out.println(event.getPayload().getSize()+" commits to "+event.getRepo().getName());
      else if (event.getType().equals("WatchEvent")) {
        System.out.println("starred "+event.getRepo().getName());
      }
      else if (event.getType().equals("PullRequestEventReview")) {
        System.out.println("pull request closed at "+event.getRepo().getName());
      }
      else if (event.getType().equals("PullRequestEvent")) {
        if (event.getPayload().getAction().equals("opened")) {
          System.out.println("pull request opened at "+event.getRepo().getName());
        }else{
          System.out.println("pull request closed at "+event.getRepo().getName());
        }
      }
      else if (event.getType().equals("CreateEvent")) {
        System.out.println("created repo "+event.getRepo().getName());
      }
    }

  }
}
