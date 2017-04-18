package hemant.com.currencyconverter.datarequestservice;

/**
 *
 * Interface to pass Notification event to associated presenter.
 * When any query (Network or DB) is complete.
 *
 * Created by Hemant on 4/10/17.
 */
public interface INotificationListener extends INotifyObject {
	
	void OnNotificationEvent(int notification, INotifyObject notificationData);
}
