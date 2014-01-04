package chan.retailer.model;

public class SimpleModel implements Model{

	private Object item;
	public SimpleModel(Object data) {
		this.item = data;
	}
	
	@Override
	public Object getModelData() {
		return this.item;
	}

}
