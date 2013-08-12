package mall.training;

import mall.training.helper.MyLoginWidgetProductionHelper;
import mall.widgetti.helper.WidgetHelper;

import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;

import tooltwist.ecommerce.AutomaticUrlParametersMode;
import tooltwist.ecommerce.RoutingUIM;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.PageImportCodeInserter;
import tooltwist.wbd.SnippetParam;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdProductionHelper;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSession;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;
import tooltwist.wbd.WbdWidgetController;

public class MyLoginWidget extends WbdWidgetController{
	private static final String WIDGET_NAME = "myLoginWidget";

	@Override
	protected void init(WbdWidget instance) throws WbdException {
		instance.defineProperty( new WbdStringProperty("email", null, "Email", ""));
		instance.defineProperty( new WbdStringProperty("password", null, "Password", ""));
	}

	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance,
			UimData ud, WbdRenderHelper rh) throws WbdException {
		render(generator, instance, ud, rh);
	}

	@Override
	public void renderForPreview(WbdGenerator generator, WbdWidget instance,
			UimData ud, WbdRenderHelper rh) throws WbdException {
		render(generator, instance, ud, rh);
	}

	@Override
	public String getLabel(WbdWidget instance) throws WbdException {
		// TODO Auto-generated method stub
		return "My Login Widget";
	}

	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance,
			UimHelper ud, WbdRenderHelper rh) throws Exception {
		SnippetParam[] params = { new SnippetParam("url", RoutingUIM.navpointUrl(ud, WbdSession.getNavpointId(ud.getCredentials()), AutomaticUrlParametersMode.NO_AUTOMATIC_URL_PARAMETERS)),
				new SnippetParam("handlerId", instance.getRequestHandlerId(generator, WIDGET_NAME)),
				new SnippetParam("email", instance.getFinalProperty(generator,"email")),
				new SnippetParam("password", instance.getFinalProperty(generator,"password"))}; 
		rh.beforeProductionCode(generator, instance, params, true);
		rh.renderSnippetForProduction( generator, instance, WidgetHelper.getJSPProdFormatFileName(WIDGET_NAME));
		rh.afterProductionCode(generator, instance);
	}

	@Override
	public void getCodeInserters(WbdGenerator generator, WbdWidget instance,
			UimData ud, CodeInserterList codeInserterList) throws WbdException {
		codeInserterList.add(WbdProductionHelper.codeInserter(instance, MyLoginWidgetProductionHelper.class.getName(), null));
		codeInserterList.add(new PageImportCodeInserter(MyLoginWidgetProductionHelper.class.getName()));

		
	}
	
	public void render(WbdGenerator generator, WbdWidget instance,
			UimData ud, WbdRenderHelper rh) throws WbdException{
		SnippetParam[] params = { new SnippetParam("url", ""),
				new SnippetParam("handlerId", ""),
				new SnippetParam("email", instance.getFinalProperty(generator,"email")),
				new SnippetParam("password", instance.getFinalProperty(generator,"password"))}; 
		rh.renderSnippet(generator, instance, WidgetHelper.getHTMLFormatFileName(WIDGET_NAME), params);
	}

}
