package hemant.com.currencyconverter.models;

import hemant.com.currencyconverter.datarequestservice.INotifyObject;

/**
 * This is a common class for all types of error responses.
 *
 * Created by Hemant on 4/10/17.
 */

public class ErrorResponse implements INotifyObject {
    private int errorCode;
    private String errorResponse;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(String errorResponse) {
        this.errorResponse = errorResponse;
    }
}
