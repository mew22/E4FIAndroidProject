package com.example.kvin.jamtogether.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kvin.jamtogether.Group;
import com.example.kvin.jamtogether.Instrument;
import com.example.kvin.jamtogether.Invitation;
import com.example.kvin.jamtogether.Music;
import com.example.kvin.jamtogether.MyApplication;
import com.example.kvin.jamtogether.Profil;
import com.example.kvin.jamtogether.R;
import com.example.kvin.jamtogether.Session;
import com.example.kvin.jamtogether.fragments.dummy.DummyContent;
import com.example.kvin.jamtogether.interfaces.Callbacks;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
/**
 * A list fragment representing a list of Items. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link ItemDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ItemListFragment extends ListFragment {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */


    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List list = null;
        Class c = null;
        switch(((MyApplication)getActivity().getApplication()).item.getItemId()){ //for each menu item in nav

            case R.id.nav_search:{
                list = ((MyApplication)getActivity().getApplication()).list_profil;
                c = Profil.class;
                break;
            }
            case R.id.nav_groups:{
                list = ((MyApplication)getActivity().getApplication()).list_group;
                c = Group.class;
                break;
            }
            case R.id.nav_music:{
                list = ((MyApplication)getActivity().getApplication()).list_music;
                c = Music.class;
                break;
            }
            case R.id.nav_instruments:{
                list = ((MyApplication)getActivity().getApplication()).list_instrument;
                c = Instrument.class;
                break;
            }
            case R.id.nav_sessions:{
                list = ((MyApplication)getActivity().getApplication()).list_session;
                c = Session.class;
                break;
            }
            case R.id.nav_invitations:{
                list = ((MyApplication)getActivity().getApplication()).list_invitation;
                c = Invitation.class;
                break;
            }
        }

        // TODO: replace with a real list adapter.
        setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                DummyContent.ITEMS));

        // setListAdapter(new ArrayAdapter<c>(getActivity(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, list));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }
    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }
}