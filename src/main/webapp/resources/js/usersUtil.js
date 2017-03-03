/**
 * Created by Admin on 03.03.2017.
 */
var ajaxUrl = 'ajax/admin/users/';
var datatableApi;

$(function () {
    datatableApi = $('#datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        });
    makeEditable();
});