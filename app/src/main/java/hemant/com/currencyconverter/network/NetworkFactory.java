package hemant.com.currencyconverter.network;

/**
 * Provide NetworkConnection Handler instance.
 *
 * Created by Hemant on 4/10/17.
 */
public class NetworkFactory implements INetworkFactory {

	private INetworkRequestHandler mNetworkConnectionHandler;

	@Override
	public INetworkRequestHandler GetNetworkConnectionHandler() {
		if (mNetworkConnectionHandler == null) {
			mNetworkConnectionHandler = new NetworkTaskHandler();
		}
		return mNetworkConnectionHandler;
	}
}
