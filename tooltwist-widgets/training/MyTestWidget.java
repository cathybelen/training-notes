package mall.training;

import mall.training.helper.MyTestWidgetProductionHelper;
import mall.widgetti.helper.WidgetHelper;
import tooltwist.ecommerce.AutomaticUrlParametersMode;
import tooltwist.ecommerce.RoutingUIM;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.CodeInsertionPosition;
import tooltwist.wbd.JavascriptCodeInserter;
import tooltwist.wbd.PageImportCodeInserter;
import tooltwist.wbd.Snippet.SnippetLocation;
import tooltwist.wbd.SnippetParam;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdProductionHelper;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSession;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;
import tooltwist.wbd.WbdWidgetController;

import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;

public class MyTestWidget extends WbdWidgetController {
	private static final String WIDGET_NAME = "myTestWidget";

	@Override
	protected void init(WbdWidget instance) throws WbdException {
		instance.defineProperty( new WbdStringProperty("edp", null, "EDP", "8035471"));
	}

	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException {
		SnippetParam[] params = { new SnippetParam("url", ""),
				new SnippetParam("handlerId", ""),
				new SnippetParam("edp", instance.getFinalProperty(generator,"edp"))}; 
		rh.renderSnippet(generator, instance, WidgetHelper.getHTMLFormatFileName(WIDGET_NAME), params);
	}

	@Override
	public void renderForPreview(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException {
		rh.append("Render Preview");
	}

	@Override
	public String getLabel(WbdWidget instance) throws WbdException {
		return "My Test Widget";
	}

	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper rh) throws Exception {
		SnippetParam[] params = { new SnippetParam("url", RoutingUIM.navpointUrl(ud, WbdSession.getNavpointId(ud.getCredentials()), AutomaticUrlParametersMode.NO_AUTOMATIC_URL_PARAMETERS)),
				new SnippetParam("handlerId", instance.getRequestHandlerId(generator, WIDGET_NAME)),
				new SnippetParam("edp", instance.getFinalProperty(generator,"edp"))}; 
		rh.beforeProductionCode(generator, instance, params, true);
		rh.renderSnippetForProduction( generator, instance, WidgetHelper.getJSPProdFormatFileName(WIDGET_NAME));
		rh.afterProductionCode(generator, instance);
	}

	@Override
	public void getCodeInserters(WbdGenerator generator, WbdWidget instance, UimData ud, CodeInserterList codeInserterList) throws WbdException {
		codeInserterList.add(new StylesheetCodeInserter(generator, instance, SnippetLocation.THEMEABLE, WidgetHelper.getCSSFormatFileName(WIDGET_NAME), null, CodeInsertionPosition.HEADER));
		codeInserterList.add(new JavascriptCodeInserter(generator, instance, SnippetLocation.THEMEABLE, WidgetHelper.getJavaScriptFormatFileName(WIDGET_NAME), null, CodeInsertionPosition.BOTTOM));
		codeInserterList.add(WbdProductionHelper.codeInserter(instance, MyTestWidgetProductionHelper.class.getName(), null));
		codeInserterList.add(new PageImportCodeInserter(MyTestWidgetProductionHelper.class.getName()));
	}
}
