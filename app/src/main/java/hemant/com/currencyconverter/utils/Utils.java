package hemant.com.currencyconverter.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

import hemant.com.currencyconverter.*;
import hemant.com.currencyconverter.models.TransactionCountModel;

/**
 * Utility class for application
 *
 * Created by Hemant on 4/10/17.
 */

public class Utils {

    private static TransactionCountModel mTransactionCountModel;

    private static int CURRENT_RESPONSE_INDEX = 1;
    private static final int MAX_RESPONSE_INDEX = 2;

    private static final String TRANSACTION_DETAILS_FILE_NAME = "transactions_";
    private static final String TRANSACTION_RATES_FILE_NAME = "rates_";
    private static final String RESPONSE_FILE_EXTENSION = ".json";
    private static final String RESPONSES_DIRECTORY = "responses";

    private static int getCurrentResponseFileIndex() {
        if(CURRENT_RESPONSE_INDEX > MAX_RESPONSE_INDEX) {
            CURRENT_RESPONSE_INDEX = CURRENT_RESPONSE_INDEX / MAX_RESPONSE_INDEX;
        }
        return CURRENT_RESPONSE_INDEX++;
    }

    public static void resetTransactionsResponse() {
        CURRENT_RESPONSE_INDEX = 1;
    }

    public static String getTransactionDetailsResult() {
        String filename = TRANSACTION_DETAILS_FILE_NAME+getCurrentResponseFileIndex()+RESPONSE_FILE_EXTENSION;
        try {
            return readFromAssets(filename);
        }catch (IOException err) {
            return null;
        }
    }

    public static String getTransactionRates() {
        int index = CURRENT_RESPONSE_INDEX;
        String filename = TRANSACTION_RATES_FILE_NAME+(index-1)+RESPONSE_FILE_EXTENSION;
        try {
            return readFromAssets(filename);
        }catch (IOException err) {
            return null;
        }
    }

    private static String readFromAssets(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(hemant.com.currencyconverter.CurrencyConverter.getAppInstance().getAssets().open(RESPONSES_DIRECTORY + File.separator + filename)));
        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine); // process line
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }

    public static void setTransactionCountModel(TransactionCountModel transactionCountModel) {
        mTransactionCountModel = transactionCountModel;
    }

    public static TransactionCountModel getTransactionCountModel() {
        return mTransactionCountModel;
    }

    public static String getGBPSign() {
        Locale locale = Locale.UK;
        Currency curr = Currency.getInstance(locale);
        return curr.getSymbol();
    }

    public static String convertToTwoDecimalPlaces(double currencyConverted) {
        DecimalFormat df = new DecimalFormat("#.##");
        currencyConverted = Double.valueOf(df.format(currencyConverted));
        return String.valueOf(currencyConverted);
    }

}
