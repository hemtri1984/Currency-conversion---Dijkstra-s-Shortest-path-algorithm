package hemant.com.currencyconverter.presenter;

import android.content.Context;

import hemant.com.currencyconverter.datarequestservice.IPresenterNotifier;
import hemant.com.currencyconverter.datarequestservice.PresenterNotifier;

/**
 * Factory class for Presenter, returns the associated presenter object for
 * an activity, also returns the attached Notification center object which is used
 * to send the result to right presenter.
 *
 * Created by Hemant on 4/10/17.
 *
 */
public class PresenterFactory implements IPresenterFactory {

	private static IPresenterFactory sPresenterFactory;
	private PresenterNotifier mPresenterNotifier;

	public PresenterFactory(Context context) {
	}

	public IPresenterFactory GetPresenterFactory() {
		return sPresenterFactory;
	}

	public static void SetPresenterFactory(IPresenterFactory platform) {
		sPresenterFactory = platform;
	}

	@Override
	public IPresenterNotifier GetPresenterNotifier() {
		if (mPresenterNotifier == null) {
			mPresenterNotifier = new PresenterNotifier();
		}
		return mPresenterNotifier;
	}
}
