function CustomerDTO(customerId, customerName, gender, contact, nic, address, email) {
    var __customerId = customerId;
    var __customerName = customerName;
    var __gender = gender;
    var __contact = contact;
    var __nic = nic;
    var __address = address;
    var __email = email;

    this.setCustomerId = function (e) {
        __customerId = e;
    }
    this.getCustomerId = function () {
        return __customerId;
    }

    this.setCustomerName = function (e) {
        __customerName = e;
    }
    this.getCustomerName = function () {
        return __customerName;
    }

    this.setGender = function (e) {
        __gender = e;
    }
    this.getGender = function () {
        return __gender;
    }

    this.setContact = function (e) {
        __contact = e;
    }
    this.getContact = function () {
        return __contact;
    }

    this.setNIC = function (e) {
        __nic = e;
    }
    this.getNIC = function () {
        return __nic;
    }

    this.setAddress = function (e) {
        __address = e;
    }
    this.getAddress = function () {
        return __address;
    }

    this.setEmail = function (e) {
        __email = e;
    }
    this.getEmail = function () {
        return __email;
    }

}