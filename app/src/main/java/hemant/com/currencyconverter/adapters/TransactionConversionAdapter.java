package hemant.com.currencyconverter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import hemant.com.currencyconverter.R;
import hemant.com.currencyconverter.adapters.viewholders.TransactionDataViewHolder;
import hemant.com.currencyconverter.models.TransactionAmount;

/**
 * Created by Hemant on 4/11/17.
 */

public class TransactionConversionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TransactionAmount> mTransactionAmountList;

    public TransactionConversionAdapter(List<TransactionAmount> transactionAmountList) {
        mTransactionAmountList = transactionAmountList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransactionDataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_data_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TransactionAmount transactionAmount = mTransactionAmountList.get(position);
        ((TransactionDataViewHolder)holder).setTransactionAmount(transactionAmount);
    }

    @Override
    public int getItemCount() {
        return (null != mTransactionAmountList ? mTransactionAmountList.size() : 0);
    }
}
