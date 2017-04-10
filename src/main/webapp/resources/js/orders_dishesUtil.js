/**
 * Created by Admin on 14.03.2017.
 */
var ajaxUrl = '/ajax/orders_dishes/';
var ajaxOrdersUrl = '/ajax/orders/update';
var datatableApi;
var redirectOrders = 'orders';

var addTitleKey ="dishes.add";

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

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
        "createdRow": "",
        "initComplete": makeEditable
    });
});

function renderPlusBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-default" onclick="plus('+row.id+');">'+
            '<span class="glyphicon glyphicon-plus"></span></a>';
    }
}

function renderMinusBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-default" onclick="minus('+row.id+');">'+
            '<span class="glyphicon glyphicon-minus"></span></a>';
    }
}

function plus(id) {
    var index = '#' + id;
    var d = datatableApi.row(index).data();
    d.quantity++;
    datatableApi
        .row(index)
        .data( d )
        .draw();
}

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

function complete() {
    $.ajax({
        type: "POST",
        url: ajaxOrdersUrl,
        data: getRequestParam(datatableApi.rows().data() ),
        success: function () {
            location.href = redirectOrders;
        }
    });
}

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