package com.yiluxiangbei.joey.haoqiu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yiluxiangbei.joey.haoqiu.R;

public class SearchActivity extends AppCompatActivity {

    private Toolbar toolbar;

    public static final String APPKEY = "656615aa9b037d931f9f9addca3809e0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar= (Toolbar) findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setFocusable(true);
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new QueryListener());
        searchView.setSubmitButtonEnabled(false);
        searchView.setQueryHint("请输入要查询的球队");
        if (searchView==null){
            return true;
        }
        SearchView.SearchAutoComplete textView= (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        textView.setTextColor(Color.WHITE);
        textView.setHintTextColor(Color.parseColor("#e0e0e0"));


        return super.onCreateOptionsMenu(menu);
    }

    private class QueryListener implements SearchView.OnQueryTextListener{

        @Override
        public boolean onQueryTextSubmit(String query) {
            if (query.length()==0){
                Toast.makeText(SearchActivity.this,"请输入球队名后再点击查询",Toast.LENGTH_SHORT).show();
            }
            Intent intent=new Intent(SearchActivity.this,TableTeamMatchActivity.class);
            intent.putExtra("club",query);
            startActivity(intent);

            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    }


}
