package com.example.kvin.jamtogether.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.kvin.jamtogether.MyApplication;
import com.example.kvin.jamtogether.R;
import com.example.kvin.jamtogether.activity.ItemDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends IntermediateListFragment {

    public boolean mTwoPane;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(((MyApplication)getActivity().getApplication()).item.getItemId() == R.id.nav_search){

        }else if(((MyApplication)getActivity().getApplication()).item.getItemId() == R.id.nav_invitations){
            inflater.inflate(R.menu.item_list_menu_without_add, menu);
        }else{
            inflater.inflate(R.menu.item_list_menu, menu);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add:
                ((MyApplication)getActivity().getApplication()).adapter.notifyDataSetChanged();
                //DialogFragment dialog = new PeopleDialog();
                //dialog.show(getSupportFragmentManager(), "PeopleDialog");
                return true;
            case R.id.clear:
                //ShowClearDialog();
                return true;
            case R.id.sort:
                //ShowSortDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_item_app_bar, container, false);

        if (v.findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            ((MyApplication)getActivity().getApplication()).TwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ItemListFragment) getChildFragmentManager()
                    .findFragmentById(R.id.item_list))
                    .setActivateOnItemClick(true);
        }
        return v;
    }


    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(getActivity(), ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
