package mall.training.handler;

import java.io.IOException;

import javax.servlet.ServletException;

import mall.widgetti.helper.WidgetHelper;
import tooltwist.wbd.WbdCache;
import tooltwist.wbd.WbdRequestHandler;
import tooltwist.wbd.WbdSession;

import com.dinaa.DinaaException;
import com.dinaa.data.XData;
import com.dinaa.data.XNodes;
import com.dinaa.ui.UimHelper;
import com.dinaa.xpc.Xpc;

public class MyTestWidgetRequestHandler extends WbdRequestHandler {

	@Override
	public boolean handler(UimHelper uh, String widgetId, String method) throws DinaaException, ServletException, IOException {
		String edp = WidgetHelper.getDefaultIfEmpty(uh.getRequestValue("edp"), "");
		if (edp.length()>0) {
			Xpc xpc = new Xpc(uh.getCredentials());
			xpc.start("my.test.widget.xpc", "get");
			xpc.attrib("edp", edp);
			xpc.attrib("price", "0");
			xpc.attrib("catalog", WbdCache.getProperty("catalogID"));
			xpc.attrib("store", WbdCache.getStoreName());
			XData data = xpc.run();
			
			System.out.println(data.getXml());
			
			XNodes list = data.getNodes("/select/dbo.usp_prd_GetProductDetails.1");
			if (list.next()) {
				WbdSession.setTemporaryValue(uh.getCredentials(), "mytestwidget.name", list.getText("Name"));
			}   
		}
		return false;
	}
}
