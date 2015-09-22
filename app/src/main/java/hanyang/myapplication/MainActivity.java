package hanyang.myapplication;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import hanyang.myapplication.controllers.CommunityManager;
import hanyang.myapplication.controllers.helpers.DataListener;
import hanyang.myapplication.models.TestData;
import hanyang.myapplication.views.adapters.TestRecyclerViewAdapter;

/**
 * This project I use MVC model to practice. 4.0+
 * Use Volley to get data from server, use Picasso to loadImage
 * One Activity with RecyclerView, use Listener to keep data sync
 * Add refresh on scroll.
 *
 * Hint: could use Volley to load image, and the CommunityManger could
 * init in Application class. Could use Fragment to implement.
 * XML style, V-21 and before, Landscape view...
 *
 * If industrial project, will do above. Just a frame
 *
 */

public class MainActivity extends AppCompatActivity implements DataListener {
    private RecyclerView mRecyclerView;
    private CommunityManager mCommunityManager;
    private TestRecyclerViewAdapter mAdapter;
    private SwipeRefreshLayout mSwapRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCommunityManager = new CommunityManager(this);
        mCommunityManager.getTestData();
        mRecyclerView = (RecyclerView) findViewById(R.id.mainActivityRecyclerView);
        mSwapRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.mainActivitySwipeRefreshLayout);
        mSwapRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCommunityManager.getTestData();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new TestRecyclerViewAdapter(this,mCommunityManager.getData());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateData(ArrayList<TestData> data) {
        mSwapRefreshLayout.setRefreshing(false);
        mAdapter.updateData(mCommunityManager.getData());
        mAdapter.notifyDataSetChanged();
    }
}
