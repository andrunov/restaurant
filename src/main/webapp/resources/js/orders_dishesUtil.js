/**
 * Class serves orders_dishes.jsp
 * use to update exist orders
 * and as final 4-th step of creation new order
 * - specify the dishes quantities
 */

/*url for exchange JSON data between DataTable and server*/
var ajaxUrl = '/ajax/orders_dishes/';

/*url for finally load data to server*/
var ajaxOrdersUrl = '/ajax/orders/update';

/*variable links to DataTable represents dishes and dishes quantities in orders_dishes.jsp*/
var datatableApi;

/*variable links to dishes.add resource bundle */
var addTitleKey ="dishes.add";

/*function to update DataTable by data from server*/
function updateTable() {
    $.get(ajaxUrl, updateTableByData,showTotalPrice());
    setTimeout(showTotalPrice,50)
}

/*document.ready function*/
$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        rowId: 'id',
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "description"
            },
            {
                "data": "price"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "className": "dt-center",
                "render": renderPlusBtn
            },
            {
                "data": "quantity",
                "className": "dt-center"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "className": "dt-center",
                "render": renderMinusBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "className": "dt-center",
                "render": renderSetToSero
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": ""
    });
    // setTimeout(showTotalPrice,50)
});

/*render function draw button for set quantity to zero*/
function renderSetToSero(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-danger" onclick="setToSero(' + row.id + ');">'+
            '<span class="glyphicon glyphicon-remove-circle"></span></a>';
    }
}

/*render function draw button for increase quantity of current Dish by 1*/
function renderPlusBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-default" onclick="plus('+row.id+');">'+
            '<span class="glyphicon glyphicon-plus"></span></a>';
    }
}

/*render function draw button for decrease quantity of current Dish by 1*/
function renderMinusBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-default" onclick="minus('+row.id+');">'+
            '<span class="glyphicon glyphicon-minus"></span></a>';
    }
}

/*function for increase quantity of current Dish by 1*/
function plus(id) {
    var index = '#' + id;
    var d = datatableApi.row(index).data();
    d.quantity++;
    datatableApi
        .row(index)
        .data( d )
        .draw();
    showTotalPrice();
}

/*function for decrease quantity of current Dish by 1*/
function minus(id) {
    var index = '#' + id;
    var d = datatableApi.row(index).data();
    d.quantity--;
    if (d.quantity <= 0){
        d.quantity = 0;
    }
    datatableApi
        .row(index)
        .data( d )
        .draw();
    showTotalPrice();
}

/*function for set to zero quantity of current Dish */
function setToSero(id) {
    var index = '#' + id;
    var d = datatableApi.row(index).data();
    d.quantity = 0;
    datatableApi
        .row(index)
        .data( d )
        .draw();
    showTotalPrice();
}

/*function for finally load data to server
* and redirect to exact page from which
 * this page (orders_dishes.jsp) was called*/
function complete() {
    $.ajax({
        type: "POST",
        url: ajaxOrdersUrl,
        data: getRequestParam(datatableApi.rows().data() ),
        success: function () {
            location.href = localStorage.getItem("ordersDishesPostRedirectUrl");
        }
    });
}

/*function to get arrays of dishes and according dishes quantities
* ignore dishes with null quantities*/
function getRequestParam(arr) {
    var dishIds=[];
    var dishQuantityValues=[];
    var dishNullQuantityIndexes=[];
    for (var i = 0; i < arr.length; i++){
        if (arr[i].quantity == 0){
            dishNullQuantityIndexes.push(i)
        }else {
            dishQuantityValues.push(arr[i].quantity)
        }
    }
    for (var i = 0; i < arr.length; i++){
        if (($.inArray(i,dishNullQuantityIndexes))==-1){
            dishIds.push(arr[i].id)
        }
    }
    return "dishIds=" + dishIds+"&dishQuantityValues="+ dishQuantityValues+"&totalPrice="+accountTotalPrice();
}

/*show accounted total price in page*/
function showTotalPrice() {
    $('#totalPrice').html(accountTotalPrice());
}

/*account actual total price */
function accountTotalPrice() {
    var totalPrice=0;
    var data = datatableApi.rows().data();
    data.each(function (value, index) {
        totalPrice = totalPrice + value.price*value.quantity;
    });
    return totalPrice.toFixed(2);
}