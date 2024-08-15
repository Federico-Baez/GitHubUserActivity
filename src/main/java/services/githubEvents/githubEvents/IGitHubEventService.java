package services.githubEvents.githubEvents;

import services.githubEvents.githubEvents.entities.Event;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IGitHubEventService {
  @GET("/users/{username}/events/public")
  Call<Event[]> getUserEvents(@Path("username") String username);

  @GET("/users/{username}/events/public")
  Call<Event[]> getUserEvents(@Path("username") String username, @Query("per_page") int per_page, @Query("page") int page);

  @GET("/repos/{username}/{repo}/events")
  Call<Event[]> getRepoEvents(@Path("username") String username, @Path("repo") String repo);

  @GET("/repos/{username}/{repo}/events")
  Call<Event[]> getRepoEvents(@Path("username") String username, @Path("repo") String repo, @Query("per_page") int per_page, @Query("page") int page);

}
