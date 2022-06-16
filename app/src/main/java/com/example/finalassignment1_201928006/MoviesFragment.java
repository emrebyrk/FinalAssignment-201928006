package com.example.finalassignment1_201928006;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {

    List<MovieClass> LIST_TRENDING_MOVIE;
    List<MovieClass> LIST_ACTION_MOVIES;
    List<MovieClass> LIST_COMEDY_MOVIES;
    RecyclerView recyclerView;
    RecyclerView recyclerViewActionMovies;
    RecyclerView recyclerViewComedyMovies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = view.findViewById(R.id.recycler_view_trending_movies);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewActionMovies = view.findViewById(R.id.recycler_view_action_movies);
        recyclerViewActionMovies.setLayoutManager(linearLayoutManager1);
        recyclerViewActionMovies.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewComedyMovies = view.findViewById(R.id.recycler_view_comedy_movies);
        recyclerViewComedyMovies.setLayoutManager(linearLayoutManager2);
        recyclerViewComedyMovies.setHasFixedSize(true);

        LIST_TRENDING_MOVIE = new ArrayList<>();
        loadTrendingMoviesData();

        LIST_ACTION_MOVIES = new ArrayList<>();
        loadActionMoviesData();

        LIST_COMEDY_MOVIES = new ArrayList<>();
        loadComedyMoviesData();
    }

    private void loadTrendingMoviesData(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.TRENDING_MOVIES_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray results = jsonObject.getJSONArray("results");
                            for (int i=0; i<10; i++){
                                JSONObject currentMovie = results.getJSONObject(i);
                                MovieClass movieClass = new MovieClass(
                                        currentMovie.getString("vote_average"),
                                        currentMovie.getString("original_title"),
                                        currentMovie.getString("poster_path"),
                                        currentMovie.getString("backdrop_path"),
                                        currentMovie.getString("overview"),
                                        currentMovie.getString("release_date"),
                                        "df"
                                );
                                progressDialog.dismiss();
                                LIST_TRENDING_MOVIE.add(movieClass);
                                TrendingMoviesAdapter trendingMoviesAdapter
                                        = new TrendingMoviesAdapter(LIST_TRENDING_MOVIE, getActivity());
                                recyclerView.setAdapter(trendingMoviesAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void loadActionMoviesData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.ACTION_MOVIES_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray results = jsonObject.getJSONArray("results");
                            for (int i=0; i<10; i++){
                                JSONObject currentMovie = results.getJSONObject(i);
                                MovieClass movieClass = new MovieClass(
                                        currentMovie.getString("vote_average"),
                                        currentMovie.getString("original_title"),
                                        currentMovie.getString("poster_path"),
                                        currentMovie.getString("backdrop_path"),
                                        currentMovie.getString("overview"),
                                        currentMovie.getString("release_date"),
                                        "df"
                                );
                                LIST_ACTION_MOVIES.add(movieClass);
                                TrendingMoviesAdapter trendingMoviesAdapter
                                        = new TrendingMoviesAdapter(LIST_ACTION_MOVIES, getActivity());
                                recyclerViewActionMovies.setAdapter(trendingMoviesAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void loadComedyMoviesData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.COMEDY_MOVIES_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray results = jsonObject.getJSONArray("results");
                            for (int i=0; i<10; i++){
                                JSONObject currentMovie = results.getJSONObject(i);
                                MovieClass movieClass = new MovieClass(
                                        currentMovie.getString("vote_average"),
                                        currentMovie.getString("original_title"),
                                        currentMovie.getString("poster_path"),
                                        currentMovie.getString("backdrop_path"),
                                        currentMovie.getString("overview"),
                                        currentMovie.getString("release_date"),
                                        "df"
                                );
                                LIST_COMEDY_MOVIES.add(movieClass);
                                TrendingMoviesAdapter trendingMoviesAdapter
                                        = new TrendingMoviesAdapter(LIST_COMEDY_MOVIES, getActivity());
                                recyclerViewComedyMovies.setAdapter(trendingMoviesAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
