package com.example.elena.crunchtime;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //sets up the gridview
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
            }
        });

        //sets up the exercise list
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercises_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        OnItemSelectedListener spinnerListener = new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
                String text = parent.getItemAtPosition(pos).toString();
                updateVals(MainActivity.this);
                updateType(MainActivity.this);
            }

            public void onNothingSelected(AdapterView<?> parent){}
        };
        spinner.setOnItemSelectedListener(spinnerListener);


        //sets listener for user input
        EditText etValue = (EditText) findViewById(R.id.userInput);
        etValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    updateVals(MainActivity.this);
                    return true;
                }
                return false;
            }
        });
    }

    //updates the calories and exercises
    protected void updateVals(Context c){
        Resources res = c.getResources();
        String[] text = res.getStringArray(R.array.exercises_array);
        //get user's input of reps/mins
        EditText etValue = (EditText) findViewById(R.id.userInput);
        int val;
        if(etValue.getText().toString().equals("")){
            val = 0;
        } else {
            val = Integer.parseInt(etValue.getText().toString());
        }
        //get user's selected exercise
        Spinner mySpinner= (Spinner) findViewById(R.id.spinner);
        String userExercise = mySpinner.getSelectedItem().toString();
        int index = Arrays.asList(text).indexOf(userExercise);

        //updates the calorie count
        int[] conversion = res.getIntArray(R.array.exercises_conversion);
        int newCals = 100*val/conversion[index];
        TextView cals = (TextView) findViewById(R.id.numCals);
        cals.setText(Integer.toString(newCals));


        //updates the reps for the other exercises
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ImageAdapter gridAdapter = (ImageAdapter) gridview.getAdapter();
        for (int i = 0; i < conversion.length; i++){
            gridAdapter.updateNums(i, newCals);
        }
        gridAdapter.notifyDataSetChanged();
    }

    //updates the 'reps is' or 'minutes are' aspect of the view
    protected  void updateType(Context c){
        Resources res = c.getResources();
        String[] text = res.getStringArray(R.array.exercises_array);
        String[] types = res.getStringArray(R.array.exercises_types);
        //get user's selected exercise
        Spinner mySpinner= (Spinner) findViewById(R.id.spinner);
        String userExercise = mySpinner.getSelectedItem().toString();
        int index = Arrays.asList(text).indexOf(userExercise);

        String repType = types[index];
        TextView typeVal = (TextView) findViewById(R.id.exerciseType);
        typeVal.setText(repType);
    }
}
