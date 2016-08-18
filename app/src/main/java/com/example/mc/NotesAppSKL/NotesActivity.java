package com.example.mc.NotesAppSKL;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class NotesActivity extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int position = 0;
    List<Note> listNotes = new LinkedList<Note>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View NotesView;

    private SearchView searchView;
    private ListView listView;


    /*private String[] movieList = {
            "Action Movie-1",
            "Action Movie-2",
            "Action Movie-3",
            "Action Movie-4",
            "Action Movie-5",
            "Comedy Movie-1",
            "Comedy Movie-2",
            "Comedy Movie-3",
            "Comedy Movie-4",
            "Comedy Movie-5",
    };*/

    private ArrayList<String> filtMovieList = new ArrayList<>();
    private boolean searchActive = false;


    //private OnFragmentInteractionListener mListener;

    public NotesActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotesActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static NotesActivity newInstance(String param1, String param2) {
        NotesActivity fragment = new NotesActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static void orderby(int sortOption) {
        System.out.println("Calling " + sortOption);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MySQLiteHelper db = new MySQLiteHelper(this.getContext());

        listNotes = db.getAllNotes();

        if (listNotes.isEmpty()) {
            db.addNote(new Note("Note one","Toronto","My new note","",""));
            db.addNote(new Note("Note two","Ajax","My second trip","",""));
            db.addNote(new Note("Note three","Sarnia","My Lambton College","",""));
            listNotes = db.getAllNotes();
        }

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        NotesView = inflater.inflate(R.layout.activity_notes, container, false);

        //get my visual obj here
        listView = (ListView) NotesView.findViewById(R.id.listView);
        searchView = (SearchView) NotesView.findViewById(R.id.searchView);

        MySQLiteHelper db = new MySQLiteHelper(this.getContext());
        listNotes = db.getAllNotes();



        for (Note note: listNotes) {
            filtMovieList.add(note.title);
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, filtMovieList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("List item clicked!");
                //String movieName = (String) parent.getItemAtPosition(position);
                //new AlertDialog.Builder(view.getContext())
                        //.setTitle("Movie Selected")
                        //.setMessage(movieName)
                        //.setCancelable(false)
                        //.setPositiveButton("ok", null).create().show();
                Intent i = new Intent(getContext(),NewnoteActivity.class);
                Note note = listNotes.get(position);
                i.putExtra("intID", note.id);
                startActivity(i);

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filtMovieList.clear();

                if (!newText.isEmpty()) {
                    for (Note note: listNotes) {
                        if (note.title.contains(newText)) {
                            filtMovieList.add(note.title);
                        }
                    }
                    searchActive = !filtMovieList.isEmpty();
                } else {
                    searchActive = false;
                }

                if (!searchActive) {
                    for (Note note: listNotes) {
                        filtMovieList.add(note.title);
                    }
                }

                ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
                adapter.notifyDataSetChanged();

                return false;
            }
        });
        //Return myView Inflated
        return NotesView;
    }

    @Override
    public void onResume(){
        super.onResume();
        //Codigo aqui para programar
        System.out.println("Notes Activity +++onResume+++");

    }

 }
