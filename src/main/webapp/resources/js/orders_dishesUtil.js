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

/*url for redirect to orders.jsp after POST method*/
var redirectOrdersUrl = 'orders';

/*variable links to dishes.add resource bundle */
var addTitleKey ="dishes.add";

/*function to update DataTable by data from server*/
function updateTable() {
    $.get(ajaxUrl, updateTableByData);
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
                "render": renderDeleteBtn
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
});

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
}

/*function for finally load data to server*/
function complete() {
    $.ajax({
        type: "POST",
        url: ajaxOrdersUrl,
        data: getRequestParam(datatableApi.rows().data() ),
        success: function () {
            location.href = redirectOrdersUrl;
        }
    });
}

/*function to get arrays of dishes and according dishes quantities*/
function getRequestParam(arr) {
    var dishIds=[];
    for (var i = 0; i < arr.length; i++){
        dishIds.push(arr[i].id)
    }
    var dishQuantityValues=[];
    for (var i = 0; i < arr.length; i++){
        dishQuantityValues.push(arr[i].quantity)
    }
    return "dishIds=" + dishIds+"&dishQuantityValues="+ dishQuantityValues;
}