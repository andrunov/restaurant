/**
 * Created by Admin on 11.03.2017.
 */
//URL variables
var ajaxUrl = '/ajax/orders/';
var ajaxRestaurantUrl = '/ajax/restaurants/';
var ajaxMenuListUrl = '/ajax/menuLists/';
var ajaxDishesUrl = '/ajax/dishes/';
var setRestaurantUrl = '/ajax/restaurants/set/';
var setMenuListUrl = '/dishes/';
var goOrdersDishes = '/orders_dishes/';

//main form datatableAPI (orders table)
var datatableApi;
var dishDTApi;

//title variables - must have equivalents in Resource Bundle
var editTitleKey ="orders.edit";
var addTitleKey ="orders.add";
var selectRestaurantKey ="restaurants.select";
var selectMenuListKey ="menuLists.select";
var selectDishesKey ="dishes.select";

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

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
                "data": "restaurant",
                "render": function (date, type, row) {
                    return (date.name +', '+ date.address);
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
                "render": renderEditOrderBtn
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

    $('#restaurantDT').DataTable({
        "ajax": {
            "url": ajaxRestaurantUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "address"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": selectRestaurantBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": "",
        "initComplete": ""
    });

    //datetimepicker
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
        return '<a class="btn btn-primary" onclick=location.href="' +goOrdersDishes + row.id +'&'+  row.restaurant.id+'">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}

function renderEditOrderBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick="updateOrderRow(' + row.id +','+  row.restaurant.id+');">' +
            '<span class="glyphicon glyphicon-time"></span></a>';
    }
}

function updateOrderRow(id,restaurantId) {
    $('#modalTitle').html(i18n[editTitleKey]);
    $.get(ajaxUrl + id +'&'+ restaurantId, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(
                key === "dateTime" ? formatDate(value) : value
            );
        });
        $('#editRow').modal();
    });
}

function addOrder() {
    $('#modalTitle2').html(i18n[selectRestaurantKey]);
    $('#selectRestaurant').modal();
}

function selectRestaurantBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick="openMenuListWindow(' + row.id +');">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}


function openMenuListWindow(id) {

    //set current restaurant
    $.ajax({
        type: "GET",
        url: setRestaurantUrl+id,
        success: function(data){
            $('#modalTitle35').html(data);
        }
    });

    //Datatable for menuList modal window re-initialisation
    $('#menuListDT').DataTable({
        "ajax": {
            "url": ajaxMenuListUrl,
            "dataSrc": ""
        },
        "destroy": true,
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "description"
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
                "orderable": false,
                "defaultContent": "",
                "render": selectMenuListBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": "",
        "initComplete": ""
    });
    $('#modalTitle3').html(i18n[selectMenuListKey]);
    $('#selectMenuList').modal();
    $('#selectRestaurant').modal('hide');

}

function selectMenuListBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick="openDishWindow(' + row.id +');">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}

function openDishWindow(id) {

    //set current menuList
    $.ajax({
        type: "GET",
        url: setMenuListUrl+id
    });

    //Datatable for dishes modal window re-initialisation
    dishDTApi = $('#dishDT').DataTable({
        "ajax": {
            "url": ajaxDishesUrl,
            "dataSrc": ""
        },
        "destroy": true,
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "description"
            },
            {
                "data": "price"
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": "",
        "initComplete": ""
    });
    $('#dishDT').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );
    $('#modalTitle4').html(i18n[selectDishesKey]);
    $('#selectDishes').modal();
    $('#selectMenuList').modal('hide');

}

function complete() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: getIndexesArr(dishDTApi.rows( '.selected' ).data() ),
        success: function () {
            $('#selectDishes').modal('hide');
            updateTable();
        }
    });
}

function getIndexesArr(arr) {
    var dishIds=[];
    for (var i = 0; i < arr.length; i++){
        dishIds.push(arr[i].id)
    }
    return "id= &dateTime=2017-01-15%2009%3A26&dishIds=" + dishIds;
}