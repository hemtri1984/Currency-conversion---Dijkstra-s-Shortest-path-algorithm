package hemant.com.currencyconverter;

import android.support.multidex.MultiDexApplication;

import hemant.com.currencyconverter.network.NetworkFactory;
import hemant.com.currencyconverter.network.NetworkFactoryLocator;
import hemant.com.currencyconverter.presenter.PresenterFactory;
import hemant.com.currencyconverter.presenter.PresenterFactoryLocator;

/**
 * Application object class. This object creates only first time when application start. Whenever
 * user sends application into background using HOME button, or application exited using BACK
 * button, its instance is saved by Android OS Application Stack. This instance is also used to pass
 * the data between difference activities.
 *
 * Created by Hemant on 4/10/17.
 */

public class CurrencyConverter extends MultiDexApplication {
    /**
     * Application class object static instance. This instance can be invoked by all the activities
     * of application.
     */
    private static CurrencyConverter mInstance;

    public static CurrencyConverter getAppInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        //Initialize presenter and network factories
        PresenterFactoryLocator.SetPresenterFactory(new PresenterFactory(this));
        NetworkFactoryLocator.SetNetworkFactory(new NetworkFactory());

        //TODO init database locator if required
    }
}
