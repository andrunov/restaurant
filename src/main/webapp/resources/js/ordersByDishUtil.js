/**
 * Created by Admin on 17.04.2017.
 */
var ajaxUrl = '/ajax/order_by_dish/';
var goOrdersDishes = '/orders_dishes_by_user/';

var datatableApi;

$(function () {
    datatableApi = $('#ordersDT').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
                "render": function (date, type, row) {
                    if (type == 'display') {
                        return formatDate(date);
                    }
                    return date;
                }
            },
            {
                "data": "user",
                "render": function (date, type, row) {
                    return (date.name);
                }
            },
            {
                "data": "user",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return '<a href="mailto:' + data.email + '">' + data.email + '</a>';
                    }
                    return data.email;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": linkBtn
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

function linkBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick=location.href="' +goOrdersDishes + row.id +'&'+  row.user.id +'">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}