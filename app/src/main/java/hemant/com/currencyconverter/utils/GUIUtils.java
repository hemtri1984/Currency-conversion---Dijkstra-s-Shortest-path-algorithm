package hemant.com.currencyconverter.utils;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import hemant.com.currencyconverter.R;

/**
 * Created by Hemant on 4/10/17.
 */

public class GUIUtils {

	public static void showProgress(Activity activity) {
		RelativeLayout progressRelativeLayout = (RelativeLayout) activity.findViewById(R.id.progress_layout);
		if (progressRelativeLayout != null) {
			progressRelativeLayout.setVisibility(View.VISIBLE);
		}
	}

	public static void hideProgress(Activity activity) {
		RelativeLayout progressRelativeLayout = (RelativeLayout) activity.findViewById(R.id.progress_layout);
		if (progressRelativeLayout != null) {
			progressRelativeLayout.setVisibility(View.GONE);
		}
	}
}
