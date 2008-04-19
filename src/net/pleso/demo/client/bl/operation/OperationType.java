package net.pleso.demo.client.bl.operation;

import java.util.ArrayList;

import net.pleso.framework.client.bl.IEnum;
import net.pleso.framework.client.bl.IEnumItem;
import net.pleso.framework.client.bl.impl.DBIntEnum;

public class OperationType extends DBIntEnum {

	private static ArrayList items = new ArrayList();

	private static DBIntEnum nullItem = null;

	private static IEnumItem[] itemsArray = null;

	protected OperationType(int value, String caption) {
		super(value, caption);
	}

	protected OperationType(String nullCaption) {
		super(nullCaption);
	}

	protected OperationType() {

	}

	public static final OperationType NullValue = new OperationType(
			"(not selected)");

	public static final OperationType Deposit = new OperationType(1, "Income");

	public static final OperationType Exchenge = new OperationType(2,
			"Exchange");

	public static final OperationType Tax = new OperationType(3, "Tax");

	public static final OperationType Credit = new OperationType(4, "Credit");

	public static IEnum getEnum() {
		return new OperationType();
	}

	public IEnumItem[] getItems() {
		if (itemsArray == null || itemsArray.length != items.size()) {
			itemsArray = new IEnumItem[items.size()];
			for (int i = 0; i < items.size(); i++)
				itemsArray[i] = (IEnumItem) items.get(i);
		}

		return itemsArray;
	}

	public IEnumItem getNullItem() {
		if (nullItem == null)
			throw new IllegalArgumentException(
					"Null enumeration item not specified.");

		return nullItem;
	}

	protected void addItem(DBIntEnum item) {
		if (item.getDBValue().isNull())
			nullItem = item;
		else
			items.add(item);
	}
}
