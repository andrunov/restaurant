/**
 * Class serves dishes.jsp
 * works with dishes of specific menuList
 */

/*url for exchange JSON data between DataTable and server*/
var ajaxUrl = '/ajax/dishes/';

/*url for link to order_by_dish.jsp*/
var goOrdersByDish = '/orders_by_dish/';

/*variable links to DataTable represents dishes in dishes.jsp*/
var datatableApi;

/*variable links to dishes.edit internationalization resource */
var editTitleKey ="dishes.edit";

/*variable links to dishes.add internationalization resource */
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

/*function for link to order_by_dish.jsp*/
function linkBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick=location.href="' +goOrdersByDish + row.id +'">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}