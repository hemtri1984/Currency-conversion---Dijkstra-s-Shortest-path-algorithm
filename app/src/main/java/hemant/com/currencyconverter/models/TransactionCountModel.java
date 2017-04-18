package hemant.com.currencyconverter.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hemant.com.currencyconverter.datarequestservice.INotifyObject;

/**
 * Singleton class contains the details of total transactions
 *
 * Created by Hemant on 4/10/17.
 */

public class TransactionCountModel implements INotifyObject {

    private static TransactionCountModel instance;
    private static final Integer initCount = 0;

    private HashMap<String, Integer> transactionsCount;
    private HashMap<String, List<TransactionAmount>> transactionAmountDetails;

    private TransactionCountModel() {
        transactionsCount = new HashMap<>();
        transactionAmountDetails = new HashMap<>();
    }

    public static TransactionCountModel getTransactionCountModel() {
        //double locking method for faster execution
        if(instance == null) {
            synchronized(initCount) {
                if(instance == null) {
                    instance = new TransactionCountModel();
                }
            }
        }
        return instance;
    }

    public void addTransaction(TransactionDetailsModel transactionDetailsModel) {
        String sku = transactionDetailsModel.getSku();

        //set Transaction Amount model
        TransactionAmount transactionAmount = new TransactionAmount();
        transactionAmount.setAmount(transactionDetailsModel.getAmount());
        transactionAmount.setCurrency(transactionDetailsModel.getCurrency());
        transactionAmount.setGbpCurrency(transactionDetailsModel.getGbpCurrency());

        if(transactionsCount.containsKey(sku)) {
            //if already there is a SKU, then increase the transaction count.
            int count = transactionsCount.get(sku).intValue();
            transactionsCount.put(sku, ++count);

            //add the transaction amount details into list
            List<TransactionAmount> transactionAmountList = transactionAmountDetails.get(sku);
            if(transactionAmountList == null) {
                transactionAmountList = new ArrayList<>();
            }
            transactionAmountList.add(transactionAmount);
            transactionAmountDetails.put(sku, transactionAmountList);
        }else {
            transactionsCount.put(sku, 0);

        }
    }

    public HashMap<String, Integer> getAllTransactions() {
        return transactionsCount;
    }

    public HashMap<String, List<TransactionAmount>> getAllTransactionDetails() {
        return transactionAmountDetails;
    }

    public void clearAllData() {
        transactionsCount.clear();
        transactionAmountDetails.clear();
    }

}
