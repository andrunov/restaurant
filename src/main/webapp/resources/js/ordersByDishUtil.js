/**
 * Created by Admin on 17.04.2017.
 */
var ajaxUrl = '/ajax/order_by_dish/';
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
            // {
            //     "orderable": false,
            //     "defaultContent": "",
            //     "render": linkBtn
            // },
            // {
            //     "orderable": false,
            //     "defaultContent": "",
            //     "render": renderDeleteBtn
            // }
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
