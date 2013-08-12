package mall.training.handler;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringEscapeUtils;

import mall.widgetti.helper.WidgetHelper;
import mall.widgetti.security.UserDetails;

import com.dinaa.DinaaException;
import com.dinaa.data.XData;
import com.dinaa.data.XNodes;
import com.dinaa.ui.UimHelper;
import com.dinaa.xpc.Xpc;

import tooltwist.wbd.WbdCache;
import tooltwist.wbd.WbdRequestHandler;
import tooltwist.wbd.WbdSession;

public class MyLoginWidgetRequestHandler extends WbdRequestHandler {

	@Override
	public boolean handler(UimHelper uh, String widgetId, String method)
			throws DinaaException, ServletException, IOException {
		String email = WidgetHelper.getDefaultIfEmpty(uh.getRequestValue("email"), "");
		String password = WidgetHelper.getDefaultIfEmpty(uh.getRequestValue("password"), "");
		
		if(isUserAuthenticated(uh, email, password)){
			WbdSession.setTemporaryValue(uh.getCredentials(), "myLoginWidget.status", "success");
		}else{
			WbdSession.setTemporaryValue(uh.getCredentials(), "myLoginWidget.status", "fail");
		}
		return false;
	}
	
		private boolean isUserAuthenticated(UimHelper uh, String emailAddress, String password) throws DinaaException {
		
		logger.debug("isUserAuthenticated ... ");
		
		if (emailAddress != null && emailAddress.length() > 0)
			emailAddress = StringEscapeUtils.unescapeHtml(emailAddress);
		
		if (password != null && password.length() > 0)
			password = StringEscapeUtils.unescapeHtml(password);
		
		Xpc xpc = uh.getXpc();
		xpc.start("mall.PC.Customers", "VerifyLogin");
		xpc.attrib("Email", emailAddress);
		xpc.attrib("Password", password);
		xpc.attrib("Unit", Integer.parseInt(WbdCache.getProperty("unit")));
		XData userData = xpc.run();
		
		if (!userData.getRootType().equals("norecordfound")) 
		{
			XNodes info = userData.getNodes("/*/dbo.acc_Verify_Login_eSub.1");
			// We are authenticated
			if (info.next()) {
				// Create a session Variable
				String userIdentifier = info.getText("SHOPPER_ID").trim();
				String currentUserIndentifier=WbdSession.getUserIdentifier(uh.getCredentials());
				
				WbdSession.setUserIdentifier(uh.getCredentials(), userIdentifier);			

				UserDetails userDetails = new UserDetails(StringEscapeUtils.escapeHtml(info.getText("FirstName").trim()), StringEscapeUtils.escapeHtml(info.getText("LastName").trim()), 
															info.getText("MI").trim(), info.getText("Customer_ID").trim());
				try {
					WbdSession.setTemporaryValue(uh.getCredentials(), "myLoginWidget.name", userDetails.getFirstName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			} else
				return false;
		} else
			return false;
	}
	
}
