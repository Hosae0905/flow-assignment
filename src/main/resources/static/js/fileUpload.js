$(document).ready(function () {
    $('#uploadButton').click(function () {
        let file = $('#fileInput')[0]

        let formData = new FormData()
        formData.append('file', file.files[0])

        console.log(formData)

        $.ajax({
            url: 'http://localhost:7070/upload/file',
            type: 'POST',
            data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success: function (response) {
                console.log(response)
            },
            error: function (error) {
                console.log(error)
            }
        })
    })
})
