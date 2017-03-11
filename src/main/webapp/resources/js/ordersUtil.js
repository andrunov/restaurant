/**
 * Created by Admin on 11.03.2017.
 */
var ajaxUrl = '/ajax/orders/';
var datatableApi;
var editTitleKey ="orders.edit";
var addTitleKey ="orders.add";

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
                "data": "dateTime"
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
        return '<a class="btn btn-primary" onclick=location.href="/dishes/'+ row.id +'">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}

