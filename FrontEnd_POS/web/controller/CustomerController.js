var regExCusName = /^[A-Z|a-z\s]{3,20}$/;
var regExCusGender = /^[^0-9](female)|(male)|(Female)|(Male)$/;
var regExCusContact = /^(071-|077-|075-|078-|)[0-9]{7}$/;
var regExCusNIC = /^[0-9]{9}(v)$/;
var regExCusAddress = /^[0-9A-Z a-z,/:]{4,50}$/;
var regExCusEmail = /^[0-9A-Z a-z$&#]{3,10}(@gmail.com)|(@yahoo.com)$/;

$("#nameOfCustomer").keyup(function (event) {

    let name = $("#nameOfCustomer").val();
    if (regExCusName.test(name)) {
        $("#nameOfCustomer").css('border', '2px solid green');
        $("#errorName").text("");
        if (event.key == "Enter") {
            $("#gender").focus();
        }
    } else {
        $("#nameOfCustomer").css('border', '2px solid red');
        $("#errorName").text("Customer Name is a required field: Min 3, Max 20, Spaces Allowed.");
    }
});

$("#gender").keyup(function (event) {

    let gender = $("#gender").val();
    if (regExCusGender.test(gender)) {
        $("#gender").css('border', '2px solid green');
        $("#errorGender").text("");
        if (event.key == "Enter") {
            $("#contact").focus();
        }
    } else {
        $("#gender").css('border', '2px solid red');
        $("#errorGender").text("Gender is a required field: Female or Male");
    }
});

$("#contact").keyup(function (event) {

    let contact = $("#contact").val();
    if (regExCusContact.test(contact)) {
        $("#contact").css('border', '2px solid green');
        $("#errorContact").text("");
        if (event.key == "Enter") {
            $("#nic").focus();
        }
    } else {
        $("#contact").css('border', '2px solid red');
        $("#errorContact").text("Contact is a required field: Pattern 07x-xxxxxxx");
    }
});

$("#nic").keyup(function (event) {

    let nic = $("#nic").val();
    if (regExCusNIC.test(nic)) {
        $("#nic").css('border', '2px solid green');
        $("#errorNIC").text("");
        if (event.key == "Enter") {
            $("#address").focus();
        }
    } else {
        $("#nic").css('border', '2px solid red');
        $("#errorNIC").text("NIC is a required field: 9 Digit and v");
    }
});

$("#address").keyup(function (event) {

    let address = $("#address").val();
    if (regExCusAddress.test(address)) {
        $("#address").css('border', '2px solid green');
        $("#errorAddress").text("");
        if (event.key == "Enter") {
            $("#email").focus();
        }
    } else {
        $("#address").css('border', '2px solid red');
        $("#errorAddress").text("Address is a required field: Min 4");
    }
});

$("#email").keyup(function (event) {

    $("#tblCustomer tbody > tr").off("click");
    $("#tblCustomer tbody > tr").off("dblclick");

    let email = $("#email").val();
    if (regExCusEmail.test(email)) {
        $("#email").css('border', '2px solid green');
        $("#errorEmail").text("");

        if (event.key == "Enter") {
            addCustomer();
        }
    } else {
        $("#email").css('border', '2px solid red');
        $("#errorEmail").text("Email is a required field: At least 6 characters with symbols");
    }

});

$('#customerId,#nameOfCustomer,#gender,#contact,#nic,#address,#email').keydown(function (e) {
    if (e.key == "Tab") {
        e.preventDefault();
    }
});

function generateCustomerIds() {
    $("#customerId").val("C00-0001");

    var test = "id";

    $.ajax({
        url: "http://localhost:8081/Maven_POS_war/customer?test="+test,
        method: "GET",
        success: function (response) {
            var customerId = response.data;
            var tempId = parseInt(customerId.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                $("#customerId").val("C00-000" + tempId);
            } else if (tempId <= 99) {
                $("#customerId").val("C00-00" + tempId);
            } else if (tempId <= 999) {
                $("#customerId").val("C00-0" + tempId);
            } else {
                $("#customerId").val("C00-" + tempId);
            }
        },
        error: function (ob) {
        }

    });
}

var tblCustomerRow;

$("#btnSaveCustomer").click(function () {

    $("#tblCustomer tbody > tr").off("click");
    $("#tblCustomer tbody > tr").off("dblclick");

    if ($("#errorId").text() != "" || $("#errorName").text() != "" || $("#errorGender").text() != "" || $("#errorContact").text() != "" || $("#errorNIC").text() != "" || $("#errorAddress").text() != "" || $("#errorEmail").text() != "" ||
        $("#customerId").val() == "" || $("#nameOfCustomer").val() == "" || $("#gender").val() == "" || $("#contact").val() == "" || $("#nic").val() == "" || $("#address").val() == "" || $("#email").val() == "") {
        $("#btnSaveCustomer").disable();

    } else {
        addCustomer();
    }

});

function addCustomer() {
    let text = "Do you really want to save this Customer?";

    if (confirm(text) == true) {
        addCustomerToDB();
    } else {

    }
}

function addCustomerToDB() {

    let data = $("#customerForm").serialize();

    $.ajax({
        url: "http://localhost:8081/Maven_POS_war/customer",
        method: "POST",
        data: data,
        success: function (response) {
            if (response.code == 200){
                alert($("#customerId").val() + " "+ response.message);
            }
            loadAllCustomer();
        },
        error: function (ob) {
            alert(ob.responseJSON.message);
            loadAllCustomer();
        }
    });
}

function loadAllCustomer() {

    $.ajax({
        url: "http://localhost:8081/Maven_POS_war/customer",
        method: "GET",
        success: function (response) {

            $("#tblCustomer tbody").empty();
            for (var responseKey of response.data) {
                let raw = `<tr><td> ${responseKey.customerId} </td><td> ${responseKey.customerName} </td><td> ${responseKey.gender} </td><td> ${responseKey.contact} </td><td> ${responseKey.nic} </td><td> ${responseKey.address} </td><td> ${responseKey.email} </td></tr>`;
                $("#tblCustomer tbody").append(raw);
            }
            clear();
            clickEvent();
            generateCustomerIds();
        },
        error: function (ob) {
            alert(ob.responseJSON.message);
        }
    });

}

function clear() {
    generateCustomerIds();

    $("#nameOfCustomer").val("");
    $("#gender").val("");
    $("#contact").val("");
    $("#nic").val("");
    $("#address").val("");
    $("#email").val("");
    $("#searchCustomer").val("");

    $("#customerId").css('border', '2px solid transparent');
    $("#nameOfCustomer").css('border', '2px solid transparent');
    $("#gender").css('border', '2px solid transparent');
    $("#contact").css('border', '2px solid transparent');
    $("#nic").css('border', '2px solid transparent');
    $("#address").css('border', '2px solid transparent');
    $("#email").css('border', '2px solid transparent');
}

function clickEvent() {

    $("#tblCustomer tbody > tr").click(function () {

        tblCustomerRow = $(this);

        var id = $.trim(tblCustomerRow.children(':nth-child(1)').text());
        var name = $.trim(tblCustomerRow.children(':nth-child(2)').text());
        var gender = $.trim(tblCustomerRow.children(':nth-child(3)').text());
        var contact = $.trim(tblCustomerRow.children(':nth-child(4)').text());
        var nic = $.trim(tblCustomerRow.children(':nth-child(5)').text());
        var address = $.trim(tblCustomerRow.children(':nth-child(6)').text());
        var email = $.trim(tblCustomerRow.children(':nth-child(7)').text());

        $("#customerId").val(id);
        $("#nameOfCustomer").val(name);
        $("#gender").val(gender);
        $("#contact").val(contact);
        $("#nic").val(nic);
        $("#address").val(address);
        $("#email").val(email);

    });

    $("#tblCustomer tbody > tr").dblclick(function () {

        let text = "Are you sure you want to delete this Customer?";
        if (confirm(text) == true) {
            tblCustomerRow.remove();
            deleteCustomer();
        } else {

        }
    });
}

$("#btnClearCustomer").click(function () {
    clear();
});

$("#btnDeleteCustomer").click(function () {

    if ($("#errorId").text() != "" || $("#errorName").text() != "" || $("#errorGender").text() != "" || $("#errorContact").text() != "" || $("#errorNIC").text() != "" || $("#errorAddress").text() != "" || $("#errorEmail").text() != "" ||
        $("#customerId").val() == "" || $("#nameOfCustomer").val() == "" || $("#gender").val() == "" || $("#contact").val() == "" || $("#nic").val() == "" || $("#address").val() == "" || $("#email").val() == "") {
        $("#btnDeleteCustomer").disable();
    } else {

        let text = "Are you sure you want to delete this Customer?";
        if (confirm(text) == true) {
            tblCustomerRow.remove();
            deleteCustomer();
        } else {

        }
    }

});

function deleteCustomer() {

    searchIfCustomerAlreadyExists();

    $.ajax({
        url: "http://localhost:8081/Maven_POS_war/customer?id=" + $("#customerId").val(),
        method: "DELETE",
        success: function (response) {
            console.log(search);
            if (search == true && response.code == 200) {
                alert($("#customerId").val() + " " + response.message);
                loadAllCustomer();
            }
        },
        error: function (ob) {
            alert(ob.responseJSON.message);
            loadAllCustomer();
        }
    });
}

var search = false;

function searchIfCustomerAlreadyExists() {
    $.ajax({
        url: "http://localhost:8081/Maven_POS_war/customer/" + $("#customerId").val(),
        method: "GET",
        success: function (response) {
            if (response.data.customerId == $("#customerId").val()) {
                search = true;
            }
        },
        error: function (ob) {
            search = false;
            alert(ob.responseJSON.message);
            loadAllCustomer();
        }
    });
}

$("#btnEditCustomer").click(function () {

    if ($("#errorId").text() != "" || $("#errorName").text() != "" || $("#errorGender").text() != "" || $("#errorContact").text() != "" || $("#errorNIC").text() != "" || $("#errorAddress").text() != "" || $("#errorEmail").text() != "" ||
        $("#customerId").val() == "" || $("#nameOfCustomer").val() == "" || $("#gender").val() == "" || $("#contact").val() == "" || $("#nic").val() == "" || $("#address").val() == "" || $("#email").val() == "") {
        $("#btnEditCustomer").disable();
    } else {

        let text = "Do you really want to update this Customer?";

        if (confirm(text) == true) {
            let cusId = $("#customerId").val();
            let cusName = $("#nameOfCustomer").val();
            let cusGender = $("#gender").val();
            let cusContact = $("#contact").val();
            let cusNIC = $("#nic").val();
            let cusAddress = $("#address").val();
            let cusEmail = $("#email").val();

            $(tblCustomerRow).children(':nth-child(1)').text(cusId);
            $(tblCustomerRow).children(':nth-child(2)').text(cusName);
            $(tblCustomerRow).children(':nth-child(3)').text(cusGender);
            $(tblCustomerRow).children(':nth-child(4)').text(cusContact);
            $(tblCustomerRow).children(':nth-child(5)').text(cusNIC);
            $(tblCustomerRow).children(':nth-child(6)').text(cusAddress);
            $(tblCustomerRow).children(':nth-child(7)').text(cusEmail);

            updateCustomer();

        } else {

        }
    }
});

function updateCustomer() {

    var cusDetail = {
        customerId: $("#customerId").val(),
        customerName: $("#nameOfCustomer").val(),
        gender: $("#gender").val(),
        contact: $("#contact").val(),
        nic: $("#nic").val(),
        address: $("#address").val(),
        email: $("#email").val(),
    }

    $.ajax({
        url: "http://localhost:8081/Maven_POS_war/customer",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(cusDetail),
        success: function (response) {
            if (response.code == 200) {
                alert($("#customerId").val() + " " + response.message);
            }
            loadAllCustomer();
        },
        error: function (ob) {
            alert(ob.responseJSON.message);
            loadAllCustomer();
        }
    });
}

$("#btnSearchCustomer").click(function () {
    $.ajax({
        url: "http://localhost:8081/Maven_POS_war/customer/" + $("#searchCustomer").val(),
        method: "GET",
        success: function (response) {
            $("#customerId").val(response.data.customerId);
            $("#nameOfCustomer").val(response.data.customerName);
            $("#gender").val(response.data.gender);
            $("#contact").val(response.data.contact);
            $("#nic").val(response.data.nic);
            $("#address").val(response.data.address);
            $("#email").val(response.data.email);
        },
        error: function (ob) {
            alert(ob.responseJSON.message);
            loadAllCustomer();
        }
    });
});

$("#btnViewCustomer").click(function () {
    loadAllCustomer();
});

$("#btnNewCustomer").click(function () {
    clear();
    generateCustomerIds();
});


