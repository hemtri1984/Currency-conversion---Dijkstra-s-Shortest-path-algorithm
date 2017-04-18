package hemant.com.currencyconverter.datarequestservice;

/**
 * Notification Center that registers notification listener with a activity presenter class, so that
 * the result should transfer to correct object.
 *
 * Created by Hemant on 4/10/17.
 */
public interface IPresenterNotifier extends INotifyObject {

	/**
	 * These are notification ids, indicates the type of notification result.
	 */
	int NOTIFICATION_SUCCESS_RESULT = 0;
	int NOTIFICATION_ERROR_RESULT = -1;

	/**
	 * Requests id's
	 */
	int NOTIFICATION_GET_RATES_DATA = 1;
	int NOTIFICATION_GET_TRANSACTIONS_DATA = 2;

	/**
	 * registers the notification listener with a presenter.
	 *
	 * @param notification         notification type id.
	 * @param notificationListener registered notification listener instance.
	 */
	void RegisterNotificationListener(int notification, INotificationListener notificationListener);

	/**
	 * post the notification object to the registered presenter.
	 *
	 * @param notification Notification type
	 * @param anyWdObject  generic notification result object.
	 */
	void PostNotification(int notification, INotifyObject anyWdObject);

	/**
	 * unb-registers the notification listener with a presenter.
	 *
	 * @param notification         notification type id.
	 * @param notificationListener registered notification listener instance.
	 */
	void UnRegisterNotificationListener(int notification, INotificationListener notificationListener);
}
