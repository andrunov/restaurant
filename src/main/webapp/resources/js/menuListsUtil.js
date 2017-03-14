/**
 * Created by Admin on 03.03.2017.
 */
var ajaxUrl = '/ajax/menuLists/';
var datatableApi;
var editTitleKey ="menuLists.edit";
var addTitleKey ="menuLists.add";

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
                "data": "dateTime",
                "render": function (date, type, row) {
                    if (type == 'display') {
                        return formatDate(date);
                    }
                    return date;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": linkBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditMenuListBtn
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

    var startDate = $('#startDate');
    var endDate = $('#endDate');
    startDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',
        onShow: function (ct) {
            this.setOptions({
                maxDate: endDate.val() ? endDate.val() : false
            })
        }
    });
    endDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        formatDate: 'Y-m-d',
        onShow: function (ct) {
            this.setOptions({
                minDate: startDate.val() ? startDate.val() : false
            })
        }
    });

    $('#startTime, #endTime').datetimepicker({
        datepicker: false,
        format: 'H:i'
    });

    $('#dateTime').datetimepicker({
        format: 'Y-m-d H:i'
    });
});

function linkBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick=location.href="/dishes/'+ row.id +'">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}

function renderEditMenuListBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick="updateRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-time"></span></a>';
    }
}