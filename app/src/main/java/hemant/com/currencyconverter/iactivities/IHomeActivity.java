package hemant.com.currencyconverter.iactivities;

import hemant.com.currencyconverter.models.ErrorResponse;
import hemant.com.currencyconverter.models.TransactionCountModel;

/**
 * Created by Hemant on 4/10/17.
 */

public interface IHomeActivity {

    void showProgressBar();

    void hideProgressBar();

    void onDataLoadSuccess(TransactionCountModel transactionCount);

    void onDataLoadError(ErrorResponse errorResponse);

}
