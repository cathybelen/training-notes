package mall.training.xpc;

import mall.xpc.AbstractSPCallPlugin;
import mall.xpc.SPDataTypes;

import com.dinaa.data.XData;
import com.dinaa.xpc.XpcException;
import com.dinaa.xpc.XpcSecurity;

public class MyTestWidgetXPC extends AbstractSPCallPlugin {

	@Override
	public void initialize(XpcSecurity securityDetails, XData data) throws XpcException {
		mapConnection("ccproduct");
		mapSP("dbo.usp_prd_GetProductDetails");
		mapColumn("EDP", "edp", SPDataTypes.INT);
		mapColumn("Price_ID", "price", SPDataTypes.INT);
		mapColumn("Catalog_ID", "catalog", SPDataTypes.INT);
		mapColumn("Store", "store", SPDataTypes.STRING);
		mapCDataOutRecord("Name");
		mapCDataOutRecord("Description");
	}
}

