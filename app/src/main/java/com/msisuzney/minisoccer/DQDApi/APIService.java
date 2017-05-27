package com.msisuzney.minisoccer.DQDApi;

import com.msisuzney.minisoccer.DQDApi.model.PersonRanking;
import com.msisuzney.minisoccer.DQDApi.model.PlayerDetail;
import com.msisuzney.minisoccer.DQDApi.model.PlayerDetailBase;
import com.msisuzney.minisoccer.DQDApi.model.PlayerLeagueData;
import com.msisuzney.minisoccer.DQDApi.model.Schedule.Schedule;
import com.msisuzney.minisoccer.DQDApi.model.TeamDetail;
import com.msisuzney.minisoccer.DQDApi.model.TeamMembers;
import com.msisuzney.minisoccer.DQDApi.model.TeamSchedule;
import com.msisuzney.minisoccer.DQDApi.model.coach.Coach;
import com.msisuzney.minisoccer.DQDApi.model.leagueRanking.LeagueRanking;
import com.msisuzney.minisoccer.DQDApi.model.news.News;
import com.msisuzney.minisoccer.DQDApi.model.search.Search;
import com.msisuzney.minisoccer.DQDApi.model.specialNews.SpecialNews;
import com.msisuzney.minisoccer.DQDApi.model.specialNewsColumn.SpecialNewsColumn;
import com.msisuzney.minisoccer.DQDApi.model.twins.Twins;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * api.dongqiudi.com
 * http://api.dongqiudi.com/data/schedule?round_id=34227&gameweek=8
 */
public interface APIService {

    @GET("/data/v1/person_ranking/{number}?version=110&refer=person_ranking")
    Call<PersonRanking> getPersonRanking(@Path("number") String number, @Query("type") String type);

    @GET("/data/v1/team_ranking/{id}?version=102&type=total_ranking&refer=data_tab")
    Call<LeagueRanking> getLeagueRanking(@Path("id") String number);

    @GET("/data/team/schedule/{number}")
    Call<TeamSchedule> getTeamSchedule(@Path("number") String number);

    @GET("data/v1/schedule/{id}?version=102")
    Call<Schedule> getLeagueSchedule(@Path("id") String round_id);

    @GET
    Call<Schedule> getLeagueSchedule2(@Url String url);

    @GET("/data/team_members/{number}")
    Call<TeamMembers> getTeamMembers(@Path("number") String teamNumber);

    @GET("/app/tabs/android/{number}.json")
    Call<News> getNews(@Path("number") String number);

    @GET
    Call<News> getMoreNews(@Url String url);


    @GET("/data/detail/team/{number}")
    Call<TeamDetail> getTeamDetail(@Path("number") String number);

    @GET("/data/sample/person/{player_id}")
    Call<PlayerDetailBase> getPlayerBasicInfo(@Path("player_id") String id);

    @GET("/data/player_career_data/{player_id}?type=league")
    Call<List<PlayerLeagueData>> getPlayerLeagueData(@Path("player_id") String id);

    @GET("/data/detail/person/{player_id}")
    Call<PlayerDetail> getPlayerDetail(@Path("player_id") String id);

    @GET("/app/tabs/android/classifications.json")
    Call<SpecialNews> getSpecial();

    @GET("/old/columns/{id}")
    Call<SpecialNewsColumn> getSpecialColumns(@Path("id") String id, @Query("page") String page);

    @GET("/data/detail/person/{coach_id}")
    Call<Coach> getCoachDetail(@Path("coach_id") String id);

    @GET("/app/tabs/wall.json?version=110")
    Call<Twins> getTwins(@Query("kind") String kind);

    @GET
    Call<Twins> getMoreTwins(@Url String url);

    @GET("/search?type=all")
    Call<Search> getSearchResult(@Query("page") String page, @Query("keywords") String keyword);
}
