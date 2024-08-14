$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/file/extension/list',
        success: function (response) {
            console.log(response)
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
            console.log(error)
        }
    })
})
