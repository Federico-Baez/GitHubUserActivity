package domain.services.githubEvents;

import domain.services.githubEvents.entities.Event;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IGitHubEventService {
  @GET("/users/{username}/events/public")
  Call<Event[]> getEvents(@Path("username") String username);

  @GET("/users/{username}/events/public")
  Call<Event[]> getEvents(@Path("username") String username, @Query("per_page") int per_page, @Query("page") int page);
}
