package hemant.com.currencyconverter.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import hemant.com.currencyconverter.R;
import hemant.com.currencyconverter.adapters.TransactionDataAdapter;
import hemant.com.currencyconverter.iactivities.IHomeActivity;
import hemant.com.currencyconverter.models.CountModel;
import hemant.com.currencyconverter.models.ErrorResponse;
import hemant.com.currencyconverter.models.TransactionCountModel;
import hemant.com.currencyconverter.presenter.HomeActivityPresenter;
import hemant.com.currencyconverter.utils.GUIUtils;
import hemant.com.currencyconverter.utils.Utils;

/**
 * Created by Hemant on 4/10/17.
 */

public class HomeActivity extends AppCompatActivity implements IHomeActivity, SwipyRefreshLayout.OnRefreshListener, TransactionDataAdapter.OnItemClickListener {

    private static final String TAG = HomeActivity.class.getName();

    public static final String SKU_NAME = "sku_name";

    private HomeActivityPresenter mPresenter;
    private TransactionDataAdapter mTransactionDataAdapter;

    //Views
    private RecyclerView mRecyclerView;
    private SwipyRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadPresenter();
        loadHomeScreen();
    }

    private void loadPresenter() {
        mPresenter = new HomeActivityPresenter(this);
    }

    private void loadHomeScreen() {
        setContentView(R.layout.activity_home);

        //inflate views
        mRecyclerView = (RecyclerView)findViewById(R.id.rv_products_list);
        mSwipeRefreshLayout = (SwipyRefreshLayout)findViewById(R.id.data_refresh);

        //Set ActionBar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(getString(R.string.title_products));
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setLogo(R.drawable.ic_home);
            actionBar.setDisplayUseLogoEnabled(true);
        }

        //attach listeners
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //Call to load transaction data
        mPresenter.loadTransactionData();
    }

    @Override
    public void showProgressBar() {
        GUIUtils.showProgress(this);
    }

    @Override
    public void hideProgressBar() {
        GUIUtils.hideProgress(this);
    }

    @Override
    public void onDataLoadSuccess(TransactionCountModel transactionCount) {
        mSwipeRefreshLayout.setRefreshing(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if(mTransactionDataAdapter == null) {
            mTransactionDataAdapter = new TransactionDataAdapter(this, transactionCount.getAllTransactions());
            mRecyclerView.setAdapter(mTransactionDataAdapter);
        }else {
            mTransactionDataAdapter.updateData(transactionCount.getAllTransactions());
        }
    }

    @Override
    public void onDataLoadError(ErrorResponse errorResponse) {
        Log.d(TAG, "Eror on retriving transaction count : "+errorResponse.getErrorResponse());
        Toast.makeText(this, errorResponse.getErrorResponse(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        //Call to load transaction data
        mPresenter.loadTransactionData();
    }

    @Override
    public void onItemClick(CountModel countModel) {
        Intent intent = new Intent(this, TransactionListActivity.class);
        intent.putExtra(SKU_NAME, countModel.getSku());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        Utils.resetTransactionsResponse();
        super.onDestroy();
    }
}
