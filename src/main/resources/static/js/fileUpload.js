$(document).ready(function () {
    $('#uploadButton').click(function () {
        let file = $('#fileInput')[0]

        let formData = new FormData()
        formData.append('file', file.files[0])

        $.ajax({
            url: 'http://localhost:8080/file/upload',
            type: 'POST',
            data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success: function (response) {
                if (response.code === 'UPLOAD_001') {
                    alert(response.message)
                }
            },
            error: function (error) {
                if (error.responseJSON.code === 'EXTENSION_ERROR_004' || error.responseJSON.code === 'MIMETYPE_ERROR_001'
                || error.responseJSON.code === 'SIGNATURE_ERROR_001') {
                    alert(error.responseJSON.code)
                }
            }
        })
    })
})
