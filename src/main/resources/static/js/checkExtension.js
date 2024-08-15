$(document).on('change', function () {
    $('input[type="checkbox"][name="extension"]').on('change', function () {
        let formData = {
            extension: $(this).val(),
            option: 'default'
        };
        if (this.checked) {
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/file/extension/checked',
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
                url: 'http://localhost:8080/file/extension/unchecked',
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


