/**
 * Class serves orders_by_dish.jsp
 * works with orders of specify dish
 */

/*url for exchange JSON data between DataTable and server*/
var ajaxUrl = '/ajax/order_by_dish/';

/*variable links to orders.edit resource bundle */
var editTitleKey ="orders.edit";

/*url for link to orders_dishes.jsp*/
var goOrdersDishes = '/orders_dishes_by_user/';

/*variable links to DataTable represents orders in orders_by_dish.jsp*/
var datatableApi;

/*function to update DataTable by data from server*/
function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

/*document.ready function*/
$(function () {
    
    /*set cross-page variable "ordersDishesPostRedirectUrl" as 'orders_by_dish' for return
     * to this page (orders_by_dish.jsp) after call POST-method in orders_dishes.jsp */
    localStorage.setItem("ordersDishesPostRedirectUrl",'orders_by_dish');

    datatableApi = $('#ordersDT').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            /*add column with image depending of Status*/
            {
                "orderable": false,
                "data": "status",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        if (data=== "ACCEPTED"){
                            return '<img  src="resources/pictures/accepted.png" />';
                        }
                        else if(data=== "PREPARING"){
                            return '<img  src="resources/pictures/preparing.png" />';
                        }
                        else if(data=== "READY"){
                            return '<img  src="resources/pictures/ready.png" />';
                        }
                        else {
                            return '<img  src="resources/pictures/finished.png" />';
                        }
                    }
                    return null;
                }
            },
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

    $.datetimepicker.setLocale(localeCode);

    /*set field with datetimepicker*/
    $('#dateTime').datetimepicker({
        format: 'Y-m-d H:i'
    });
    
});

/*function for link to orders_dishes.jsp*/
function linkBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick=location.href="' +goOrdersDishes + row.id +'&'+  row.user.id +'">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}

/*render function draw button for update row*/
function renderEditBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick="updateRow(' + row.id +','+  row.user.id+');">' +
            '<span class="glyphicon glyphicon-edit"></span></a>';
    }
}

/*method to update row with new DataTime and Status*/
function updateRow(id,userId) {
    //fill modal form with data and open it
    $('#modalTitle').html(i18n[editTitleKey]);
    $.get(ajaxUrl + id+'&'+userId, function (data) {
        $.each(data, function (key, value) {
            if (key === "status") {
                $("#" + value).click();
            }else {
                $('#detailsForm').find("input[name='" + key + "']").val(
                    key === "dateTime" ? formatDate(value) : value
                );
            }
        });
        $('#editRow').modal();
    });
}