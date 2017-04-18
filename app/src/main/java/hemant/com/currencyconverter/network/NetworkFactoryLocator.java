package hemant.com.currencyconverter.network;

/**
 *
 * Getters and Setters for Network factory.
 *
 * Created by Hemant on 4/10/17.
 *
 */

public class NetworkFactoryLocator {

	private static INetworkFactory sNetworkFactory;

	private NetworkFactoryLocator() {
	}

	public static INetworkFactory GetNetworkFactory() {
		return sNetworkFactory;
	}

	public static void SetNetworkFactory(INetworkFactory sNetworkFactory) {
		NetworkFactoryLocator.sNetworkFactory = sNetworkFactory;
	}
}
