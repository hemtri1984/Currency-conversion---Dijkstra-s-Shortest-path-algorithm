package hemant.com.currencyconverter.datarequestservice;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import hemant.com.currencyconverter.CurrencyConverter;
import hemant.com.currencyconverter.R;
import hemant.com.currencyconverter.models.ErrorResponse;
import hemant.com.currencyconverter.models.RateConversionModel;
import hemant.com.currencyconverter.models.TransactionCountModel;
import hemant.com.currencyconverter.models.TransactionDetailsListModel;
import hemant.com.currencyconverter.models.TransactionDetailsModel;
import hemant.com.currencyconverter.presenter.PresenterFactoryLocator;
import hemant.com.currencyconverter.utils.IAppConstants;
import hemant.com.currencyconverter.utils.RateConversionShortestPath;
import hemant.com.currencyconverter.utils.Utils;

/**
 * This class loads the transaction data.
 * Data can be load from server, local database or assets (in our case).
 * Created by Hemant on 4/10/17.
 */

public class TransactionDataService {

    private static IPresenterNotifier mPresenterNotifier;

    private static final Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //return error
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setErrorCode(IAppConstants.ERROR_NO_RESPONSE);
                    errorResponse.setErrorResponse(CurrencyConverter.getAppInstance().getResources().getString(R.string.error_no_response));
                    mPresenterNotifier.PostNotification(IPresenterNotifier.NOTIFICATION_GET_TRANSACTIONS_DATA, errorResponse);
                    break;

                case 1:
                    //return result
                    TransactionCountModel transactionCountModel = (TransactionCountModel) msg.obj;
                    mPresenterNotifier.PostNotification(IPresenterNotifier.NOTIFICATION_GET_TRANSACTIONS_DATA, transactionCountModel);
                    break;
            }
        }
    };

    public TransactionDataService() {
        mPresenterNotifier = PresenterFactoryLocator.GetPresenterFactory().GetPresenterNotifier();
    }

    public void doGetTransactionDetails() {
        /**
         * NOTE: Here we'll check if the network is available then we'll fetch it from server, else
         * fetch the data from cache.
         * But in current scenerio we are fetching the data from asset files so eleminating the
         * network checks.
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                String transactionDetailsResult = Utils.getTransactionDetailsResult();
                String currencyConversionResult = Utils.getTransactionRates();
                if(TextUtils.isEmpty(transactionDetailsResult)||TextUtils.isEmpty(currencyConversionResult)) {
                    //return error
                    mainThreadHandler.sendEmptyMessage(0);
                }else {
                    //parse transaction data
                    Type listType = new TypeToken<List<TransactionDetailsModel>>(){}.getType();
                    List<TransactionDetailsModel> transactionDetailsList = (List<TransactionDetailsModel>)(new Gson().fromJson(transactionDetailsResult, listType));

                    //parse rate conversion data
                    Type rateConvListType = new TypeToken<List<RateConversionModel>>(){}.getType();
                    List<RateConversionModel> rateConversionList = (List<RateConversionModel>)(new Gson().fromJson(currencyConversionResult, rateConvListType));

                    //set it into transaction model
                    TransactionDetailsListModel transactionDetailsListModel = new TransactionDetailsListModel();
                    transactionDetailsListModel.setAllTransactionDetails(transactionDetailsList);
                    transactionDetailsListModel.setRateConversionModel(rateConversionList);

                    TransactionCountModel transactionCountModel = TransactionCountModel.getTransactionCountModel();
                    transactionCountModel.clearAllData();
                    List<RateConversionModel> rateConversionListModel = transactionDetailsListModel.getRateConversionModel();
                    for(TransactionDetailsModel transactionDetailsModel:transactionDetailsListModel.getAllTransactionDetails()) {
                        String gbpValue = conversionRateToGBP(transactionDetailsModel.getCurrency(), transactionDetailsModel.getAmount(), rateConversionListModel);
                        transactionDetailsModel.setGbpCurrency(gbpValue);
                        transactionCountModel.addTransaction(transactionDetailsModel);
                    }

                    Message message = new Message();
                    message.what = 1;
                    message.obj = transactionCountModel;
                    mainThreadHandler.sendMessage(message);
                }
            }
        }).start();
    }

    private String conversionRateToGBP(String currency, String currencyValue, List<RateConversionModel> rateConversionModel) {
        double conversionRate = RateConversionShortestPath.getConversionValue(rateConversionModel, currency);
        if(conversionRate == 0.) {
            return null;
        }else {
            double currencyParent = Double.parseDouble(currencyValue);
            double currencyConverted = currencyParent * conversionRate;
            return Utils.convertToTwoDecimalPlaces(currencyConverted);
        }
    }

}
