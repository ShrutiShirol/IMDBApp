package com.android.app.imdbapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Santoshraddi on 14-03-2017.
 */
public class Upcoming extends Fragment {

    public Upcoming() {
    }

    View view_upComing;
    RecyclerView lv_upcoming;
    int mode =0;
    String URL_UC = "http://api.themoviedb.org/3/movie/upcoming?api_key=8496be0b2149805afa458ab8ec27560c";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onPause() {
        setRetainInstance(true);
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view_upComing = inflater.inflate(R.layout.fragment_upcoming, container, false);
        lv_upcoming = (RecyclerView)view_upComing.findViewById(R.id.recyclerView_upComing);
        loadRecyclerViewData();
        return view_upComing;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_favorites:
                mode = 1;
                DownloadJSON_Favorites_Watchlist downloadJSON_favorites_watchlist = new DownloadJSON_Favorites_Watchlist(getActivity().getApplicationContext(), lv_upcoming, mode);
                downloadJSON_favorites_watchlist.execute();
                break;
            case R.id.menu_watchlist:
                mode = 2;
                DownloadJSON_Favorites_Watchlist downloadJSON_favorites_watchlist1 = new DownloadJSON_Favorites_Watchlist(getActivity().getApplicationContext(),lv_upcoming, mode);
                downloadJSON_favorites_watchlist1.execute();
                break;
            case R.id.menu_refresh:
                new Upcoming();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void loadRecyclerViewData(){
        DownloadJSON downloadJSON = new DownloadJSON(getActivity().getApplication().getApplicationContext(), lv_upcoming);
        downloadJSON.getJSON(URL_UC);
    }

}

