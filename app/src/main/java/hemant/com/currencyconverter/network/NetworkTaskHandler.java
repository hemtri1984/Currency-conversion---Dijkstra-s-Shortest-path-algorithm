package hemant.com.currencyconverter.network;

import hemant.com.currencyconverter.datarequestservice.TransactionDataService;

/**
 * Pass all the network requests from Presenter Layer to associated Network layer.
 *
 * Created by Hemant on 4/10/17.
 */
public class NetworkTaskHandler implements INetworkRequestHandler {

	@Override
	public void doGetTransactionDetails() {
		new TransactionDataService().doGetTransactionDetails();
	}
}
