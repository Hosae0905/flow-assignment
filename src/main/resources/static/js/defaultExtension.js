$(function getDefaultExtension () {
    let option = 'default'
    $.ajax({
        type: 'GET',
        url: `http://localhost:8080/extension/list?option=${option}`,
        success: function (response) {
            if (response.code === 'EXTENSION_003') {
                let extensionList = $('#extensionList');
                response.result.forEach(function (item, index) {
                    const extension = item.extension
                    const isChecked = item.status ? 'checked' : ''
                    const checkboxId = 'ext' + index

                    extensionList.append(`
                    <input type="checkbox" id="${checkboxId}" value="${extension}" ${isChecked} name="extension">
                    <label for="${checkboxId}">${item.extension}</label>
                `)
                });
            }
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

$(function () {
    $(document).on('change', 'input[type="checkbox"][name="extension"]', function isCheckedExtension () {
        let formData = {
            extension: $(this).val(),
            option: 'default'
        };
        if (this.checked) {
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/extension/checked',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (response) {
                    if (response.code === 'EXTENSION_001') {
                        alert(response.message);
                    }
                },
                error: function (error) {
                    if (error.responseJSON.code === 'EXTENSION_ERROR_001' || error.responseJSON.code === 'EXTENSION_ERROR_002') {
                        alert(error.responseJSON.message)
                    } else if (error.code === 'SERVER_ERROR_001') {
                        alert(error.responseJSON.message)
                    }
                }
            })
        } else {
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/extension/unchecked',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (response) {
                    if (response.code === 'EXTENSION_002') {
                        alert(response.message)
                    }
                },
                error: function (error) {
                    if (error.responseJSON.code === 'EXTENSION_ERROR_001' || error.responseJSON.code === 'EXTENSION_ERROR_002') {
                        alert(error.responseJSON.message)
                    } else if (error.code === 'SERVER_ERROR_001') {
                        alert(error.responseJSON.message)
                    }
                }
            })
        }
    })
})





