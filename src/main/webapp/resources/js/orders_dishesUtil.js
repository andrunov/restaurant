/**
 * Created by Admin on 14.03.2017.
 */
var ajaxUrl = '/ajax/orders_dishes/';
var datatableApi;
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
                "render": renderPlusBtn
            },
            {
                "data": "quantity"
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
        return '<a class="btn btn-danger" onclick="inc('+row.id+');">'+
            '<span class="glyphicon glyphicon-remove-circle"></span></a>';
    }
}

function inc(id) {

    var index = '#' + id;
    var d = datatableApi.row(index).data();
    d.quantity++;
    datatableApi
        .row(index)
        .data( d )
        .draw();
}

