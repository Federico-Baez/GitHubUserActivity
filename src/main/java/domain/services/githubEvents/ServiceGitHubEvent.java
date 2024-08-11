package domain.services.githubEvents;

import domain.services.githubEvents.entities.Event;
import java.io.IOException;
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

  public Event[] getUserEventList(String username) throws IOException {
    IGitHubEventService gitHubEventService = this.retrofit.create(IGitHubEventService.class);
    Call<Event[]> requestUserEventList = gitHubEventService.getEvents(username);
    Response<Event[]> responseUserEventList = requestUserEventList.execute();
    return responseUserEventList.body();
  }

  public Event[] getUserEventListPages(String username, int items_per_page, int page) throws IOException {
    IGitHubEventService gitHubEventService = this.retrofit.create(IGitHubEventService.class);
    Call<Event[]> requestUserEventList = gitHubEventService.getEvents(username);
    Response<Event[]> responseUserEventList = requestUserEventList.execute();
    return responseUserEventList.body();
  }
}
