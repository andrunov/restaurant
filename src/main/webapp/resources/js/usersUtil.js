/**
 * Class serves users.jsp
 * works with users
 */

/*url for exchange JSON data between DataTable and server*/
var ajaxUrl = 'ajax/admin/users/';

/*url for link to orders.jsp*/
var goOrdersUrl = '/orders/';

/*variable links to DataTable represents users in users.jsp*/
var datatableApi;

/*variable links to users.edit internationalization resource */
var editTitleKey ="users.edit";

/*variable links to users.add internationalization resource */
var addTitleKey ="users.add";

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
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return '<a href="mailto:' + data + '">' + data + '</a>';
                    }
                    return data;
                }
            },
            {
                "data": "roles"
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

/*function for link to orders.jsp*/
function linkBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick=location.href="'+ goOrdersUrl + row.id +'">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}