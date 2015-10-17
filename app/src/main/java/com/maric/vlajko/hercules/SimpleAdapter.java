package com.maric.vlajko.hercules;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Vlajko on 8/31/2015.
 */
public class SimpleAdapter extends ListActivity {
    int slikeMenija [] = {R.drawable.leaf,R.drawable.leaf,R.drawable.leaf};
    String nazivMenija [] = {"Settings", "Logout", "Add"};
    List<Map<String,String>> alldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView lv = getListView();
        alldata = new ArrayList<Map<String,String>>();
    }
}
