/**
 * Class serves orders_by_dish.jsp
 * works with orders of specify dish
 */

/*url for exchange JSON data between DataTable and server*/
var ajaxUrl = '/ajax/order_by_dish/';

/*url for link to orders_dishes.jsp*/
var goOrdersDishes = '/orders_dishes_by_user/';

/*variable links to DataTable represents orders in orders_by_dish.jsp*/
var datatableApi;

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

/*function for link to orders_dishes.jsp*/
function linkBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick=location.href="' +goOrdersDishes + row.id +'&'+  row.user.id +'">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}