package mall.training.helper;

import java.util.Properties;

import mall.widgetti.helper.WidgetHelper;

import com.dinaa.data.XData;
import com.dinaa.ui.UimData;

import tooltwist.wbd.WbdProductionHelper;
import tooltwist.wbd.WbdSession;

public class MyLoginWidgetProductionHelper extends WbdProductionHelper{
	//private String email="";
	//private String password="";
	private String status="";
	private String name="";
	public MyLoginWidgetProductionHelper(Properties params) {
		super(params);
	}

	@Override
	public XData preFetch(UimData ud) throws Exception {
		//email  = WidgetHelper.getDefaultIfEmpty(WbdSession.getTemporaryValue(ud.getCredentials(), "myLoginWidget.email"), "");
		//password  = WidgetHelper.getDefaultIfEmpty(WbdSession.getTemporaryValue(ud.getCredentials(), "myLoginWidget.password"), "");
		status = WidgetHelper.getDefaultIfEmpty(WbdSession.getTemporaryValue(ud.getCredentials(), "myLoginWidget.status"), "");
		name = WidgetHelper.getDefaultIfEmpty(WbdSession.getTemporaryValue(ud.getCredentials(), "myLoginWidget.name"), "");

		return null;
	}
	/*public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}*/
	public String getStatus() {
		return status;
	}
	public String getName() {
		return name;
	}

}
