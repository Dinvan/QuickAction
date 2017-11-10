package com.advanz101.quickaction;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;

import com.advanz101.quickaction.filter.FilterModel;
import com.advanz101.quickaction.filter.FilterAdapter;
import com.advanz101.quickaction.filter.PopupHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button1 = (Button) findViewById(android.R.id.button1);
        Button button2 = (Button) findViewById(android.R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }


    @Override
    public void onClick(View anchorView) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showFilters(findViewById(R.id.filter));
        return super.onOptionsItemSelected(item);
    }

    List<FilterModel> filters;
    FilterAdapter adapter;

    public void showFilters(View anchorView) {
        PopupWindow window = PopupHelper.newBasicPopupWindow(this);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup, null);
        ExpandableListView expandableListView = (ExpandableListView) popupView.findViewById(R.id.expandableListView);
        LoadCountries();
        adapter = new FilterAdapter(this, filters);

        expandableListView.setAdapter(adapter);

        // The choice mode has been moved from list view to adapter in order
        // to not extend the class ExpansibleListView
        adapter.setChoiceMode(FilterAdapter.CHOICE_MODE_MULTIPLE);

        // Handle the click when the user clicks an any child
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                adapter.setClicked(groupPosition, childPosition);
                return false;
            }
        });

        window.setContentView(popupView);
        int totalHeight = getWindowManager().getDefaultDisplay().getHeight();
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);
        Rect anchor_rect = new Rect(location[0], location[1], location[0]
                + anchorView.getWidth(), location[1] + anchorView.getHeight());
        int position_x = 0, position_y = 0;
        int contentViewWidth = popupView.getMeasuredWidth();
        position_x = anchor_rect.centerX() - (contentViewWidth - contentViewWidth / 2);
        position_y = anchor_rect.bottom - (anchor_rect.height() / 2) + 10;

        if (location[1] < (totalHeight / 2.0)) {
            // top half of screen
            window.setAnimationStyle(R.style.Animations_PopDownMenuRight);
            window.showAsDropDown(anchorView);
        }
    }


    private void LoadCountries() {
        filters = new ArrayList<>();

        List<String> brand = new ArrayList<String>();
        brand.add("Sundrop Cooking Oil");
        brand.add("Saffola Cooking Oil");
        brand.add("Swadist Cooking Oil");
        brand.add("Fortune Cooking Oil");
        brand.add("Mahakosh Cooking Oil");

        List<String> category = new ArrayList<String>();
        category.add("Edible Oils & Ghee");


        List<String> subCategory = new ArrayList<String>();
        subCategory.add("Refined Oil");
        subCategory.add("Sunflower Oil");
        subCategory.add("Vanaspati oil");
        subCategory.add("Cottonseed oil");
        subCategory.add("Groundnut oil");
        subCategory.add("Mustard oil");

        List<String> priceRange = new ArrayList<String>();
        priceRange.add("Low to high");
        priceRange.add("High to Low");

        List<String> offers = new ArrayList<String>();
        offers.add("Get one buy one free");
        offers.add("Free Delivery");
        filters.add(new FilterModel("Brand", brand));
        filters.add(new FilterModel("Category", category));
        filters.add(new FilterModel("Sub Category", subCategory));
        filters.add(new FilterModel("Price Range", priceRange));
        filters.add(new FilterModel("Offer", offers));
    }
}
