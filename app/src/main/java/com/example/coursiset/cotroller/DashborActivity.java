package com.example.coursiset.cotroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.coursiset.R;
import com.example.coursiset.data.CoursAdapter;

public class DashborActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private StaggeredGridLayoutManager staggeredGridLayoutManager;
private CoursAdapter adapter;
public Menu menu;
public boolean Listview=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbor);
        recyclerView=findViewById(R.id.coursRecycleView);
        Listview=true;
        adapter=new CoursAdapter();
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new CoursAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(DashborActivity.this,"clicked on "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu=menu;
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_toggel) {
            toggel();
            //Toast.makeText(DashborActivity.this,"toggel ",Toast.LENGTH_SHORT).show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toggel() {
        if(Listview){showGrigView();}
        else{showlistview();}
    }

    private void showlistview() {
        staggeredGridLayoutManager.setSpanCount(1);
        MenuItem menuItem=menu.findItem(R.id.action_toggel);
        menuItem.setIcon(R.drawable.outline_view_list_white_24dp);
       menuItem.setTitle(getString(R.string.show_as_grid));

        Listview=true;
    }

    private void showGrigView() {
        staggeredGridLayoutManager.setSpanCount(2);
   MenuItem menuItem=menu.findItem(R.id.action_toggel);
  menuItem.setIcon(R.drawable.outline_grid_view_white_24dp);
 menuItem.setTitle(getString(R.string.show_as_list));
        Listview=false;
    }
}