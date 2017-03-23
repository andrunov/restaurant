/**
 * Created by Admin on 11.03.2017.
 */
//URL variables
var ajaxUrl = '/ajax/orders/';
var ajaxRestaurantUrl = '/ajax/restaurants/';
var ajaxMenuListUrl = '/ajax/menuLists/';
var ajaxDishesUrl = '/ajax/dishes/';
var setRestaurantUrl = '/ajax/restaurants/set/';
var setMenuListUrl = '/ajax/menuLists/set/';
var goOrdersDishes = '/orders_dishes/';

//main form datatableAPI (orders table)
var datatableApi;
var dishDTApi;

//title variables - must have equivalents in Resource Bundle
var editTitleKey ="orders.edit";
var addTitleKey ="orders.add";

//variable for save title for multiple use
var currentRestaurantTitle;

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
        "createdRow": ""
    });
});

function linkBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick=location.href="' +goOrdersDishes + row.id +'&'+  row.restaurant.id+'">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}

// function renderEditOrderBtn(data, type, row) {
//     if (type == 'display') {
//         return '<a class="btn btn-primary" onclick="updateOrderRow(' + row.id +','+  row.restaurant.id+');">' +
//             '<span class="glyphicon glyphicon-time"></span></a>';
//     }
// }

// function updateOrderRow(id,restaurantId) {
//     // $('#modalTitle').html(i18n[editTitleKey]);
//     $.get(ajaxUrl + id +'&'+ restaurantId, function (data) {
//         $.each(data, function (key, value) {
//             form.find("input[name='" + key + "']").val(
//                 key === "dateTime" ? formatDate(value) : value
//             );
//         });
//         $('#editRow').modal();
//     });
// }

function addOrder() {
    $('#selectRestaurant').modal();
}

function selectRestaurantBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick="openMenuListWindow(' + row.id +');">' +
            '<span class="glyphicon glyphicon-ok"></span></a>';
    }
}


function openMenuListWindow(id) {

    //set current restaurant
    $.ajax({
        type: "GET",
        url: setRestaurantUrl+id,
        success: function(data){
            $('#modalTitleRestaurant').html(data);
            currentRestaurantTitle = data;
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
    });
    $('#selectMenuList').modal();
    $('#selectRestaurant').modal('hide');

}

function selectMenuListBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick="openDishWindow(' + row.id +');">' +
            '<span class="glyphicon glyphicon-ok"></span></a>';
    }
}

function openDishWindow(id) {

    //set current menuList
    $.ajax({
        type: "GET",
        url: setMenuListUrl+id,
        success: function(data){
            $('#modalTitleRestaurant2').html(currentRestaurantTitle);
            $('#modalTitleMenuList').html(data);
        }
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
            },
            {
                'targets': 0,
                'searchable': false,
                'orderable': false,
                'width': '1%',
                'className': 'dt-body-center',
                'render': function (data, type, full, meta) {
                    return '<input type="checkbox">';
                }
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });

    // Handle click on checkbox
    $('#dishDT tbody').on('click', 'input[type="checkbox"]', function(e){
        var $row = $(this).closest('tr');

        if(this.checked){
            $row.addClass('selected');
        } else {
            $row.removeClass('selected');
        }

        e.stopPropagation();
    });
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
    return "dishIds=" + dishIds;
}

// function selectRowBtn() {
//     if (type == 'display') {
//         return '<a class="btn btn-primary" onclick="function () {  };">' +
//             '<span class="glyphicon glyphicon-saved"></span></a>';
//     }
// }