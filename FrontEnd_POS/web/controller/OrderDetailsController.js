function disableOrderFields() {
    $("#OID").prop('disabled',true);
    $("#ODate").prop('disabled',true);
    $("#cusId").prop('disabled',true);
    $("#itemQty").prop('disabled',true);
    $("#grossAmount").prop('disabled',true);
    $("#netAmount").prop('disabled',true);
}

$("#btnSearchOrders").click(function () {

    $("#tblOrderDetail tbody").empty();
    var oid = $.trim($("#searchOrderId").val());

    searchORDER(oid);
    //searchDetailsForOrder(oid);

});

function searchORDER(oid) {
    $.ajax({
        url: "http://localhost:8081/Maven_POS_war/order/" + oid,
        method: "GET",
        success: function (response) {
            $("#cusId").val(response.data.customer.customerId);
            $("#OID").val(response.data.orderId);
            $("#ODate").val(response.data.orderDate);
            $("#grossAmount").val(response.data.grossTotal);
            $("#netAmount").val(response.data.netTotal);

            $("#tblOrderDetail tbody").empty()
            for (var oDetails of response.data.orderDetails) {
                let raw = `<tr><td> ${oDetails.itemCode} </td><td> ${oDetails.itemKind} </td><td> ${oDetails.itemName} </td><td> ${oDetails.sellQty} </td><td> ${oDetails.unitPrice} </td><td> ${oDetails.itemDiscount} </td><td> ${oDetails.total} </td></tr>`;
                $("#tblOrderDetail tbody").append(raw);
            }

            searchItemQty(oid);
        },
        error: function (ob) {
            alert(ob.responseJSON.message);
        }
    });
}

function searchItemQty(oid) {
    $.ajax({
        url: "http://localhost:8081/Maven_POS_war/order/" + oid,
        method: "GET",
        success: function (response) {
            $("#itemQty").val(response);
        },
        error: function (ob, statusText, error) {
            alert("No Quantity.");
        }
    });
}

function searchDetailsForOrder(oid) {
    $.ajax({
        url: "http://localhost:8081/Maven_POS_war/order/" + oid,
        method: "GET",
        success: function (response) {
            $("#tblOrderDetail tbody").empty()
            for (var oDetails of response) {
                let raw = `<tr><td> ${oDetails.itemId} </td><td> ${oDetails.itemKind} </td><td> ${oDetails.itemName} </td><td> ${oDetails.sellQty} </td><td> ${oDetails.unitPrice} </td><td> ${oDetails.itemDiscount} </td><td> ${oDetails.total} </td></tr>`;
                $("#tblOrderDetail tbody").append(raw);
            }
        },
        error: function (ob, statusText, error) {
            alert("No Such Order Details.");
        }
    });
}

$("#btnClear").click(function () {
    $("#tblOrderDetail tbody").empty();
    $("#cusId").val("");
    $("#OID").val("");
    $("#ODate").val("");
    $("#netAmount").val("");
    $("#grossAmount").val("");
    $("#itemQty").val("");
    $("#searchOrderId").val("");
});