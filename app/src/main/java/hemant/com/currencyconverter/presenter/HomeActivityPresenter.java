package hemant.com.currencyconverter.presenter;

import android.content.Context;

import hemant.com.currencyconverter.datarequestservice.INotificationListener;
import hemant.com.currencyconverter.datarequestservice.INotifyObject;
import hemant.com.currencyconverter.datarequestservice.IPresenterNotifier;
import hemant.com.currencyconverter.iactivities.IHomeActivity;
import hemant.com.currencyconverter.models.ErrorResponse;
import hemant.com.currencyconverter.models.TransactionCountModel;
import hemant.com.currencyconverter.network.INetworkRequestHandler;
import hemant.com.currencyconverter.network.NetworkFactoryLocator;
import hemant.com.currencyconverter.utils.Utils;

/**
 * Created by Hemant on 4/10/17.
 */

public class HomeActivityPresenter implements INotificationListener {
    private static final String TAG = HomeActivityPresenter.class.getName();

    private IHomeActivity iHomeActivityListener;
    private Context mContext;

    /**
     * Notification center interface
     */
    private IPresenterNotifier mPresenterNotifier;
    private INetworkRequestHandler mNetworkRequestHandler;

    public HomeActivityPresenter(Context context) {
        mContext = context;
        iHomeActivityListener = (IHomeActivity)context;

        //locate network factory
        mPresenterNotifier = PresenterFactoryLocator.GetPresenterFactory().GetPresenterNotifier();
        mNetworkRequestHandler = NetworkFactoryLocator.GetNetworkFactory()
                .GetNetworkConnectionHandler();
    }

    public void loadTransactionData() {
        //show progress bar
        iHomeActivityListener.showProgressBar();

        //call to load transaction data
        mPresenterNotifier.RegisterNotificationListener(IPresenterNotifier.NOTIFICATION_GET_TRANSACTIONS_DATA, this);
        mNetworkRequestHandler.doGetTransactionDetails();
    }

    @Override
    public void OnNotificationEvent(int notification, INotifyObject notificationData) {
        switch (notification) {
            case IPresenterNotifier.NOTIFICATION_GET_TRANSACTIONS_DATA:
                if(notificationData instanceof TransactionCountModel) {
                    TransactionCountModel transactionCountModel = (TransactionCountModel) notificationData;

                    Utils.setTransactionCountModel(transactionCountModel);

                    //hide progress bar
                    iHomeActivityListener.hideProgressBar();

                    //Display result
                    iHomeActivityListener.onDataLoadSuccess(transactionCountModel);
                }else if(notificationData instanceof ErrorResponse) {
                    //hide progress bar
                    iHomeActivityListener.hideProgressBar();
                    //Show error response in Toast
                    ErrorResponse errorResponse = (ErrorResponse)notificationData;
                    iHomeActivityListener.onDataLoadError(errorResponse);
                }
                break;
        }
    }
}
