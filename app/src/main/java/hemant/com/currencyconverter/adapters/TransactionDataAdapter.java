package hemant.com.currencyconverter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import hemant.com.currencyconverter.R;
import hemant.com.currencyconverter.adapters.viewholders.TransactionDataViewHolder;
import hemant.com.currencyconverter.models.CountModel;

/**
 * Created by Hemant on 4/10/17.
 */

public class TransactionDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final OnItemClickListener listener;

    private List<CountModel> mTransactionsList;

    public interface OnItemClickListener {
        void onItemClick(CountModel countModel);
    }

    public TransactionDataAdapter(OnItemClickListener listener, HashMap<String, Integer> transactionCountList) {
        this.listener = listener;
        mTransactionsList = new ArrayList<>();
        Iterator iterator = transactionCountList.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            CountModel countModel = new CountModel(pair.getKey().toString(), (Integer)pair.getValue());
            mTransactionsList.add(countModel);
        }
    }

    public void updateData(HashMap<String, Integer> transactionCountList) {
        mTransactionsList.clear();
        Iterator iterator = transactionCountList.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            CountModel countModel = new CountModel(pair.getKey().toString(), (Integer)pair.getValue());
            mTransactionsList.add(countModel);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransactionDataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_data_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CountModel countModel = mTransactionsList.get(position);
        ((TransactionDataViewHolder)holder).setCountData(countModel);
        ((TransactionDataViewHolder) holder).bind(countModel, listener);
    }

    @Override
    public int getItemCount() {
        return (null != mTransactionsList ? mTransactionsList.size() : 0);
    }
}
