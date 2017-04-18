package hemant.com.currencyconverter.adapters.viewholders;

import android.text.TextUtils;
import android.view.View;

import java.util.Currency;

import hemant.com.currencyconverter.R;
import hemant.com.currencyconverter.adapters.TransactionDataAdapter;
import hemant.com.currencyconverter.models.CountModel;
import hemant.com.currencyconverter.models.TransactionAmount;
import hemant.com.currencyconverter.utils.Utils;

/**
 * Created by Hemant on 4/10/17.
 */

public class TransactionDataViewHolder extends DataViewHolder {

    public TransactionDataViewHolder(View itemView) {
        super(itemView);

    }

    public void setCountData(CountModel countModel) {
        setTitle(countModel.getSku());
        setSubtitle(String.valueOf(countModel.getCount()) +" "+itemView.getContext().getResources().getString(R.string.suffix_transactions));
    }

    public void setTransactionAmount(TransactionAmount transactionAmount) {
        setTitle(getCurrencySignFromCode(transactionAmount.getCurrency())+transactionAmount.getAmount());
        if(TextUtils.isEmpty(transactionAmount.getGbpCurrency())) {
            setSubtitle(itemView.getContext().getResources().getString(R.string.conversion_error));
        }else {
            setSubtitle(Utils.getGBPSign() + transactionAmount.getGbpCurrency());
        }
    }

    private String getCurrencySignFromCode(String currencyCode) {
        Currency currency = Currency.getInstance(currencyCode);
        return currency.getSymbol();
    }

    public void bind(final CountModel item, final TransactionDataAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }
}
