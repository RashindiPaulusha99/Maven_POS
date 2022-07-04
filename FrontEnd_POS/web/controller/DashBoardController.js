$("#customerName").css('display','none');
$("#customerSec").css('display','none');
$("#itemName").css('display','none');
$("#itemSec").css('display','none');
$("#orderName").css('display','none');
$("#orderSec").css('display','none');
$("#orderDetailName").css('display','none');
$("#orderDetailSec").css('display','none');

count();
function count() {
    countCustomers();
    countItems();
    countOrders();
    income();
}

function countCustomers() {
    $.ajax({
        url:"http://localhost:8081/Maven_POS_war/customer/COUNT/count?"+"count",
        method:"GET",
        success:function (response) {
            $("#customerCount").text(response.data);
        },
        error:function () {
        }
    });
}

function countItems() {
    $.ajax({
        url:"http://localhost:8081/Maven_POS_war/item/COUNT/count?"+"count",
        method:"GET",
        success:function (response) {
            $("#itemCount").text(response.data);
        },
        error:function () {
        }
    });
}

function countOrders() {
    $.ajax({
        url:"http://localhost:8081/Maven_POS_war/order/COUNT/count?"+"count",
        method:"GET",
        success:function (response) {
            $("#orderCount").text(response.data);
        },
        error:function () {
        }
    });
}

function income(){
    $.ajax({
        url:"http://localhost:8081/Maven_POS_war/order/INCOME/income?"+"income",
        method:"GET",
        success:function (response) {
            $("#income").text(response.data+"/=");
        },
        error:function () {
        }
    });
}

$("#customer").click(function () {
    $("#customerName").css('display','block');
    $("#customerSec").css('display','block');
    $("#itemName").css('display','none');
    $("#itemSec").css('display','none');
    $("#orderName").css('display','none');
    $("#orderSec").css('display','none');
    $("#homeSec").css('display','none');
    $("#name").css('display','none');
    $("#orderDetailName").css('display','none');
    $("#orderDetailSec").css('display','none');

    generateCustomerIds();
    $("#nameOfCustomer").focus();
});

$("#item").click(function () {
    $("#customerName").css('display','none');
    $("#customerSec").css('display','none');
    $("#itemName").css('display','block');
    $("#itemSec").css('display','block');
    $("#orderName").css('display','none');
    $("#orderSec").css('display','none');
    $("#homeSec").css('display','none');
    $("#name").css('display','none');
    $("#orderDetailName").css('display','none');
    $("#orderDetailSec").css('display','none');

    loadItemDetails();
    $("#kind").focus();
    generateItemCodes();
});

$("#home").click(function () {
    $("#customerName").css('display','none');
    $("#customerSec").css('display','none');
    $("#itemName").css('display','none');
    $("#itemSec").css('display','none');
    $("#orderName").css('display','none');
    $("#orderSec").css('display','none');
    $("#homeSec").css('display','block');
    $("#name").css('display','block');
    $("#orderDetailName").css('display','none');
    $("#orderDetailSec").css('display','none');

    count();
});

$("#order").click(function () {
    $("#customerName").css('display','none');
    $("#customerSec").css('display','none');
    $("#itemName").css('display','none');
    $("#itemSec").css('display','none');
    $("#orderName").css('display','block');
    $("#orderSec").css('display','block');
    $("#homeSec").css('display','none');
    $("#name").css('display','none');
    $("#orderDetailName").css('display','none');
    $("#orderDetailSec").css('display','none');

    loadCustomerIds();
    loadItemCodes();
    disableFields();
    generateOrderId();
});

$("#orderDetails").click(function () {
    $("#customerName").css('display','none');
    $("#customerSec").css('display','none');
    $("#itemName").css('display','none');
    $("#itemSec").css('display','none');
    $("#orderName").css('display','none');
    $("#orderSec").css('display','none');
    $("#homeSec").css('display','none');
    $("#name").css('display','none');
    $("#orderDetailName").css('display','block');
    $("#orderDetailSec").css('display','block');

    disableOrderFields();

});