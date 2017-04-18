package hemant.com.currencyconverter.presenter;


import hemant.com.currencyconverter.datarequestservice.IPresenterNotifier;

/**
 * Created by Hemant on 4/10/17.
 */
public interface IPresenterFactory {

	/**
	 * @return Attached Notification center instance.
	 */
	IPresenterNotifier GetPresenterNotifier();
}
