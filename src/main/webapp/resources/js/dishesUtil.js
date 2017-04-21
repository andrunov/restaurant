/**
 * Created by Admin on 13.03.2017.
 */
var ajaxUrl = '/ajax/dishes/';
var goOrdersByDish = '/orders_by_dish/';
var datatableApi;
var editTitleKey ="dishes.edit";
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
                "render": linkBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
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

function linkBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick=location.href="' +goOrdersByDish + row.id +'">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}