$(document).ready(function () {
    let option = 'default'
    $.ajax({
        type: 'GET',
        url: `http://localhost:8080/file/extension/list?option=${option}`,
        success: function (response) {
            let extensionList = $('#extensionList')
            response.result.forEach(function (item, index) {
                const extension = item.extension
                const isChecked = item.status ? 'checked' : ''
                const checkboxId = 'ext' + index

                extensionList.append(`
                    <input type="checkbox" id="${checkboxId}" value="${extension}" ${isChecked} name="extension">
                    <label for="${checkboxId}">${item.extension}</label>
                `)
            });
        },
        error: function (error) {
            if (error.responseJSON.code === 'EXTENSION_ERROR_002') {
                alert(error.responseJSON.message)
            } else if (error.responseJSON.code === 'SERVER_ERROR_001') {
                alert(error.responseJSON.message)
            }
        }
    })
})
