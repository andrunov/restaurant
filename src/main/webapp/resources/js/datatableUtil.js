/*
class for common JS methods
*/

/*variable for link to current form*/
var form;

/*variable for link to fail noty*/
var failedNote;

/*set fail noties to form*/
function makeEditable() {
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

/*method to add new several entities in several forms*/
function add() {
    $('#modalTitle').html(i18n[addTitleKey]);
    form[0].reset();
    $('#editRow').modal();
}

/*method to update row in tables that may includes DateTime field*/
function updateRow(id) {
    $('#modalTitle').html(i18n[editTitleKey]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(
                key === "dateTime" ? formatDate(value) : value
            );
        });
        $('#editRow').modal();
    });
}

/*method to delete row
* use in all forms*/
function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
        }
    });
}

/*redraw rows by data send from server */
function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

/*save data by AJAX*/
function save() {
    var form = $('#detailsForm');
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
        }
    });
}


/*render function draw button for update row*/
function renderEditBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary" onclick="updateRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit"></span></a>';
    }
}

/*render function draw button for delete row*/
function renderDeleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-danger" onclick="deleteRow(' + row.id + ');">'+
            '<span class="glyphicon glyphicon-remove-circle"></span></a>';
    }
}

/*removes letter T from DateTime*/
function formatDate(date) {
    return date.toString().replace('T', ' ').substr(0, 16);
}

/*clear old failedNote*/
function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

/*create noty in case of error*/
function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>" + jqXHR.responseJSON,
        type: 'error',
        layout: 'bottomRight'
    });
}