package mall.training.helper;

import java.util.Properties;

import mall.widgetti.helper.WidgetHelper;
import tooltwist.wbd.WbdProductionHelper;
import tooltwist.wbd.WbdSession;

import com.dinaa.data.XData;
import com.dinaa.ui.UimData;

public class MyTestWidgetProductionHelper extends WbdProductionHelper {
	private String name = "";
	
	public MyTestWidgetProductionHelper(Properties params) {
		super(params);
	}

	@Override
	public XData preFetch(UimData ud) throws Exception {
		name  = WidgetHelper.getDefaultIfEmpty(WbdSession.getTemporaryValue(ud.getCredentials(), "mytestwidget.name"), "");
		return null;
	}
	
	public String getName() {
		return name;
	}
}
