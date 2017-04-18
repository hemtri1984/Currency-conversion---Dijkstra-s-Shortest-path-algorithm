package hemant.com.currencyconverter.datarequestservice;

import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;

import java.util.ArrayList;

/**
 * Notification center class handles the result notification events after query to server or DB.
 * This class post the notification result to the right(registered) activity presenter class in the form of
 * INotify object.
 *
 * Created by Hemant on 4/10/17.
 *
 */
public class PresenterNotifier extends Handler implements IPresenterNotifier, INotifyObject {

	private SparseArray<ArrayList<INotifyObject>> mNotificationSparseMap;
	public PresenterNotifier() {
		mNotificationSparseMap = new SparseArray<>();
	}

	/**
	 * registers and add the notification listener into a queue to notify about the result to associated classes.
	 * (Observer pattern).
	 */
	public void RegisterNotificationListener(int notification, INotificationListener notificationListener) {
		if (mNotificationSparseMap.indexOfKey(notification) >= 0) {
			ArrayList<INotifyObject> listeners = (ArrayList<INotifyObject>) mNotificationSparseMap
					.get(notification);
			for (int i = 0; i < listeners.size(); i++) {
				INotificationListener item = (INotificationListener) listeners.get(i);
				if (item.getClass().equals(notificationListener.getClass())) {
					listeners.remove(i--);
				}
			}
			listeners.add((INotifyObject) notificationListener);
		} else {
			ArrayList<INotifyObject> listenersList = new ArrayList<>();
			listenersList.add(notificationListener);
			mNotificationSparseMap.put(notification, listenersList);
		}
	}

	/**
	 * Post the result to attached presenter class.
	 */
	public void PostNotification(int notification, INotifyObject anyWdObject) {
		Message msg = obtainMessage();
		msg.what = notification;
		msg.obj = anyWdObject;
		sendMessage(msg);
	}

	public void UnRegisterNotificationListener(int notification,
			INotificationListener notificationListener) {
		if (mNotificationSparseMap.indexOfKey(notification) >= 0) {
			ArrayList<INotifyObject> listeners = (ArrayList<INotifyObject>) mNotificationSparseMap
					.get(notification);
			listeners.remove(notificationListener);
		}
	}

	public void handleMessage(Message msg) {
		int notification = msg.what;
		INotifyObject anyWdObject = (INotifyObject) msg.obj;
		if (mNotificationSparseMap.indexOfKey(notification) >= 0) {
			ArrayList<INotifyObject> listeners = (ArrayList<INotifyObject>) mNotificationSparseMap
					.get(notification);
			for (int i = 0; i < listeners.size(); i++) {
				((INotificationListener) listeners.get(i)).OnNotificationEvent(notification,
						anyWdObject);
			}
		}
	}
}
