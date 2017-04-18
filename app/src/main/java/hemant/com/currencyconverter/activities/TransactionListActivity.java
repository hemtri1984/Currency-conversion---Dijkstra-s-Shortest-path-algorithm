package hemant.com.currencyconverter.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.List;

import hemant.com.currencyconverter.R;
import hemant.com.currencyconverter.adapters.TransactionConversionAdapter;
import hemant.com.currencyconverter.models.TransactionAmount;
import hemant.com.currencyconverter.utils.Utils;

/**
 * Created by Hemant on 4/11/17.
 */

public class TransactionListActivity extends AppCompatActivity {

    private static final String TAG = TransactionListActivity.class.getName();

    //Views
    private RecyclerView mRecyclerView;
    private SwipyRefreshLayout mSwipeRefreshLayout;
    private TextView tvTotalText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        String screenName = getIntent().getStringExtra(HomeActivity.SKU_NAME);

        //inflate views
        mRecyclerView = (RecyclerView)findViewById(R.id.rv_products_list);
        mSwipeRefreshLayout = (SwipyRefreshLayout)findViewById(R.id.data_refresh);
        tvTotalText = (TextView)findViewById(R.id.tv_total_amount);
        tvTotalText.setVisibility(View.VISIBLE);

        //Set ActionBar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(String.format(getString(R.string.transactions_for), screenName));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //attach listeners
        mSwipeRefreshLayout.setEnabled(false);

        List<TransactionAmount> transactionAmountList = Utils.getTransactionCountModel().getAllTransactionDetails().get(screenName);
        double sum = 0.;
        for(TransactionAmount transactionAmount:transactionAmountList) {
            sum += Double.parseDouble(transactionAmount.getGbpCurrency());
        }
        tvTotalText.setText(String.valueOf(String.format(getString(R.string.total_amount), Utils.getGBPSign()+String.valueOf(Utils.convertToTwoDecimalPlaces(sum)))));

        //set Adapter
        TransactionConversionAdapter transactionConversionAdapter = new TransactionConversionAdapter(transactionAmountList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(transactionConversionAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
