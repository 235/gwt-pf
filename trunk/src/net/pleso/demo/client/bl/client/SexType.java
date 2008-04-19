package net.pleso.demo.client.bl.client;

import java.util.ArrayList;

import net.pleso.framework.client.bl.IEnum;
import net.pleso.framework.client.bl.IEnumItem;
import net.pleso.framework.client.bl.impl.DBIntEnum;

public class SexType extends DBIntEnum {

	private static ArrayList items = new ArrayList();

	private static DBIntEnum nullItem = null;

	private static IEnumItem[] itemsArray = null;

	protected SexType(int value, String caption) {
		super(value, caption);
	}

	protected SexType(String nullCaption) {
		super(nullCaption);
	}

	protected SexType() {

	}

	public static final SexType NullValue = new SexType(
			"(not selected)");

	public static final SexType Male = new SexType(1, "Male");

	public static final SexType Female = new SexType(2,
			"Female");

	public static IEnum getEnum() {
		return new SexType();
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
