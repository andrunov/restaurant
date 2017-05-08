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

/*variable links to users.edit resource bundle */
var editTitleKey ="users.edit";

/*variable links to users.add resource bundle */
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
            /*add column with image depending of Role*/
            {
                "orderable": false,
                "data": "roles",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        var isAdmin = false;
                        for (var i = 0; i < data.length; i++){
                            if (data[i] === "ADMIN"){
                                isAdmin = true;
                            }
                        }
                        if (isAdmin){
                            return '<img  src="resources/pictures/admin.png" />';
                        }
                        else {
                            return '<img  src="resources/pictures/user.png" />';
                        }
                    }
                    return null;
                }
            },
            {
                "data": "name"
            },
            /*adjust e-mail column*/
            {
                "data": "email",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return '<a href="mailto:' + data + '">' + data + '</a>';
                    }
                    return data;
                }
            },
            /*internationalization of enum Role values*/
            {
                "data": "roles",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        var localRoles = [];
                        for (var i = 0; i < data.length; i++){
                            localRoles.push(i18n["roles." + data[i]])
                        }
                        return localRoles;
                    }
                    return data;
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
                1,
                "asc"
            ]
        ],
        /*customize row style depending of Role*/
        "createdRow": function (row, data, dataIndex) {
            var isAdmin = false;
            for (var i = 0; i < data.roles.length; i++){
                if (data.roles[i] === "ADMIN"){
                    isAdmin = true;
                }
            }
            $(row).addClass(isAdmin ? 'isAdmin' : 'isUser');
        },
        "initComplete": makeEditable
    });

    datatableApi.each(function(){
        if ($(this).text() == 'N') {
            $(this).css('background-color','#f00');
        }
    });
});

/*function for link to orders.jsp*/
function linkBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick=location.href="'+ goOrdersUrl + row.id +'">' +
            '<span class="glyphicon glyphicon-list-alt"></span></a>';
    }
}

/*render function draw button for update row*/
function renderEditBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick="updateRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit"></span></a>';
    }
}

/*method to update row in tables */
function updateRow(id) {
    // document.getElementById('ADMIN').checked = false;
    // document.getElementById('USER').checked = false;
    $('#modalTitle').html(i18n[editTitleKey]);
    $("#ADMIN").prop("checked", false);
    $("#USER").prop("checked", false);

    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            if (key === "roles") {
                for (var i = 0; i < value.length; i++) {
                    $("#" + value[i]).click();
                }
            }
            else {
                form.find("input[name='" + key + "']").val(value);
            }
        });
        $('#editRow').modal();
    });
}