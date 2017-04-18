package hemant.com.currencyconverter.network;

/**
 * Factory class returns network connection handler class instance.
 *
 * Created by Hemant on 4/10/17.
 *
 */
public interface INetworkFactory {

	INetworkRequestHandler GetNetworkConnectionHandler();
}
