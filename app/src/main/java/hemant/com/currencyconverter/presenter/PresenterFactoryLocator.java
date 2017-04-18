package hemant.com.currencyconverter.presenter;

/**
 * Locates the presenter factory.
 *
 * Created by Hemant on 4/10/17.
 *
 */
public class PresenterFactoryLocator {

	private static IPresenterFactory sPresenterFactory;

	public static IPresenterFactory GetPresenterFactory() {
		return sPresenterFactory;
	}

	public static void SetPresenterFactory(IPresenterFactory sPresenterFac) {
		PresenterFactoryLocator.sPresenterFactory = sPresenterFac;
	}
}
