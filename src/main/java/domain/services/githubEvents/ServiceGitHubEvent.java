package domain.services.githubEvents;

import domain.Repository;
import domain.services.githubEvents.entities.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGitHubEvent{
  private static ServiceGitHubEvent instance=null;
  private static final String urlApi="https://api.github.com/";
  private Retrofit retrofit;

  //Constructor
  private ServiceGitHubEvent(){
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  //Singleton
  public static ServiceGitHubEvent getInstance(){
    if(instance == null) {
      instance = new ServiceGitHubEvent();
    }
    return instance;
  }

  public List<Repository> getRepositories(String username) throws IOException {
    List<Repository> processedRepositories = new ArrayList<>();
    String[] availableRepos = getRepoList(fetchUserEventList(username));
    int i=0;
    while(i < availableRepos.length) {
      String repo = availableRepos[i];
      Event[] repoEvents = fetchRepoEventList(username, repo);
      processedRepositories.add(processRepoEvents(username, repo, repoEvents));
      i++;
    }
    return processedRepositories;
  }

  private Event[] fetchUserEventList(String username) throws IOException {
    IGitHubEventService gitHubEventService = this.retrofit.create(IGitHubEventService.class);
    Call<Event[]> requestUserEventList = gitHubEventService.getUserEvents(username);
    Response<Event[]> responseUserEventList = requestUserEventList.execute();
    return responseUserEventList.body();
  }

  private Event[] fetchRepoEventList(String username, String repo) throws IOException {
    IGitHubEventService gitHubEventService = this.retrofit.create(IGitHubEventService.class);
    Call<Event[]> requestRepoEventList = gitHubEventService.getRepoEvents(username, repo);
    Response<Event[]> responseRepoEventList = requestRepoEventList.execute();
    return responseRepoEventList.body();
  }

  private String[] getRepoList(Event[] events) {
    return Arrays.stream(events)
        .map(event -> event.getRepo().getName().split("/")[1])
        .distinct()
        .toArray(String[]::new);
  }

  private Repository processRepoEvents(String username, String repo, Event[] repoEvents) {
    Repository repository = new Repository(username, repo);
    //process every repo from its events using specific methods in order to populate the repository
    repository.setStars(calculateStars(repoEvents));
    repository.setBranches(calculateBranches(repoEvents));
    repository.setCommits(calculateCommits(repoEvents));
    repository.setOpenedIssues(calculateOpenedIssues(repoEvents));
    repository.setClosedIssues(calculateClosedIssues(repoEvents));
    repository.setOpenedPullRequests(calculateOpenedPullRequests(repoEvents));
    repository.setClosedPullRequests(calculateClosedPullRequests(repoEvents));
    return repository;
  }

  private int calculateStars(Event[] repoEvents) {
    return Arrays.stream(repoEvents)
        .filter(event -> event.getType().equals("WatchEvent")
            && event.getPayload().getAction().equals("started"))
        .toArray().length;
  }

  private int calculateBranches(Event[] repoEvents) {
    return Arrays.stream(repoEvents)
        .filter(event -> event.getType().equals("CreateEvent")
            && event.getPayload().getRef() != null)
        .toArray().length;
  }

  private int calculateCommits(Event[] repoEvents) {
    return Arrays.stream(repoEvents)
        .filter(event -> event.getType().equals("PushEvent"))
        .mapToInt(event -> Integer.parseInt(event.getPayload().getSize()))
        .sum();
  }

  private int calculateOpenedIssues(Event[] repoEvents) {
    return Arrays.stream(repoEvents)
        .filter(event -> event.getType().equals("IssuesEvent")
            && event.getPayload().getAction().equals("opened"))
        .toArray().length;
  }

  private int calculateClosedIssues(Event[] repoEvents) {
    return Arrays.stream(repoEvents)
        .filter(event -> event.getType().equals("IssuesEvent")
            && event.getPayload().getAction().equals("closed"))
        .toArray().length;
  }

  private int calculateOpenedPullRequests(Event[] repoEvents) {
    return Arrays.stream(repoEvents)
        .filter(event -> event.getType().equals("PullRequestEvent")
            && event.getPayload().getAction().equals("opened"))
        .toArray().length;
  }

  private int calculateClosedPullRequests(Event[] repoEvents) {
    return Arrays.stream(repoEvents)
        .filter(event -> event.getType().equals("PullRequestEvent")
            && event.getPayload().getAction().equals("closed"))
        .toArray().length;
  }


}
