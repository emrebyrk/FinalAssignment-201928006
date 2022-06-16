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

public class TVShowsFragment extends Fragment {

    RecyclerView recyclerViewTV;
    RecyclerView recyclerViewActionTvshows;
    RecyclerView recyclerViewComedyTvshows;

    List<MovieClass> LIST_TRENDING_TVSHOWS;
    List<MovieClass> LIST_ACTION_TVSHOWS;
    List<MovieClass> LIST_COMEDY_TVSHOWS;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvshows, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTV = view.findViewById(R.id.recycler_view_trending_tvshows);
        recyclerViewTV.setLayoutManager(linearLayoutManager);
        recyclerViewTV.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewActionTvshows = view.findViewById(R.id.recycler_view_action_tvshows);
        recyclerViewActionTvshows.setLayoutManager(linearLayoutManager1);
        recyclerViewActionTvshows.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewComedyTvshows = view.findViewById(R.id.recycler_view_comedy_tvshows);
        recyclerViewComedyTvshows.setLayoutManager(linearLayoutManager2);
        recyclerViewComedyTvshows.setHasFixedSize(true);

        LIST_TRENDING_TVSHOWS = new ArrayList<>();
        loadTrendingTvshowsData();

        LIST_ACTION_TVSHOWS = new ArrayList<>();
        loadActionTvshowsData();

        LIST_COMEDY_TVSHOWS = new ArrayList<>();
        loadComedyTvshowsData();
    }

    private void loadTrendingTvshowsData(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.TRENDING_TVSHOWS_REQUEST_URL,
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
                                        currentMovie.getString("original_name"),
                                        currentMovie.getString("poster_path"),
                                        currentMovie.getString("backdrop_path"),
                                        currentMovie.getString("overview"),
                                        "Not Provided",
                                        "df"
                                );
                                progressDialog.dismiss();
                                LIST_TRENDING_TVSHOWS.add(movieClass);
                                TrendingMoviesAdapter trendingMoviesAdapter
                                        = new TrendingMoviesAdapter(LIST_TRENDING_TVSHOWS, getActivity());
                                recyclerViewTV.setAdapter(trendingMoviesAdapter);
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

    private void loadActionTvshowsData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.ACTION_TVSHOWS_REQUEST_URL,
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
                                        currentMovie.getString("original_name"),
                                        currentMovie.getString("poster_path"),
                                        currentMovie.getString("backdrop_path"),
                                        currentMovie.getString("overview"),
                                        "Not Provided",
                                        "df"
                                );
                                LIST_ACTION_TVSHOWS.add(movieClass);
                                TrendingMoviesAdapter trendingMoviesAdapter
                                        = new TrendingMoviesAdapter(LIST_ACTION_TVSHOWS, getActivity());
                                recyclerViewActionTvshows.setAdapter(trendingMoviesAdapter);
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

    private void loadComedyTvshowsData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.COMEDY_TVSHOWS_REQUEST_URL,
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
                                        currentMovie.getString("original_name"),
                                        currentMovie.getString("poster_path"),
                                        currentMovie.getString("backdrop_path"),
                                        currentMovie.getString("overview"),
                                        "Not Provided",
                                        "df"
                                );
                                LIST_COMEDY_TVSHOWS.add(movieClass);
                                TrendingMoviesAdapter trendingMoviesAdapter
                                        = new TrendingMoviesAdapter(LIST_COMEDY_TVSHOWS, getActivity());
                                recyclerViewComedyTvshows.setAdapter(trendingMoviesAdapter);
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