package com.company.sales.web.orderdetails;

import com.haulmont.cuba.gui.screen.*;
import com.company.sales.entity.OrderDetails;

@UiController("sales_OrderDetails.edit")
@UiDescriptor("order-details-edit.xml")
@EditedEntityContainer("orderDetailsDc")
@LoadDataBeforeShow
public class OrderDetailsEdit extends StandardEditor<OrderDetails> {
}