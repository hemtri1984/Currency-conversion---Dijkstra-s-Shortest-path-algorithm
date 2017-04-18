package hemant.com.currencyconverter.models;

import java.util.List;

/**
 * Contains the list of all the transactions.
 *
 * Created by Hemant on 4/11/17.
 */

public class TransactionDetailsListModel {
    private List<TransactionDetailsModel> transactionDetailsModels;
    private List<RateConversionModel> rateConversionsListModel;

    public List<TransactionDetailsModel> getAllTransactionDetails() {
        return transactionDetailsModels;
    }

    public void setAllTransactionDetails(List<TransactionDetailsModel> transactionDetails) {
        transactionDetailsModels = transactionDetails;
    }

    public List<RateConversionModel> getRateConversionModel() {
        return rateConversionsListModel;
    }

    public void setRateConversionModel(List<RateConversionModel> rateConversionListModel) {
        this.rateConversionsListModel = rateConversionListModel;
    }
}
