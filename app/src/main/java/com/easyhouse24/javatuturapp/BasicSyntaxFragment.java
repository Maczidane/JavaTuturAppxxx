package com.easyhouse24.javatuturapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasicSyntaxFragment extends Fragment {

    private BasicSyntaxFragment basicSyntaxFragment;

    private IntroFragment introFragment;


    private BottomNavigationView bottomNavigationView;

    private VariablesFragment variablesFragment;



    public BasicSyntaxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_basic_syntax, container, false);

        bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.nav_tutorial_basic_syntax);
        bottomNavigationView.setSelectedItemId(R.id.tutorial_next);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.tutorial_next:
                        String prefAdvanced = getActivity().getSharedPreferences("IS_ACCEPTED", MODE_PRIVATE).getString("X_NUMBER", null);

                        if (prefAdvanced == null) {
                            Toast.makeText(getContext(), "Please read Introduction First", Toast.LENGTH_LONG).show();
                            return false;

                        } else {
                            int pref1 = Integer.valueOf(prefAdvanced);


                            if (2 > pref1) {

                                SharedPreferences pref = getActivity().getSharedPreferences("IS_ACCEPTED", Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("X_NUMBER", "2");
                                editor.commit();
                            }

                            variablesFragment = new VariablesFragment();
                            final FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                            ft1.replace(R.id.frame_tutorial, variablesFragment, "NewFragmentTag");
                            ft1.commit();
                            break;

                        }


                            case R.id.tutorial_back:
                                introFragment = new IntroFragment();
                                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.frame_tutorial, introFragment, "NewFragmentTag");
                                ft.commit();
                                break;

                        }


                        return false;
                }

        });







       return view;
    }

}
