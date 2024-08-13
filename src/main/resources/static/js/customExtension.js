$(document).ready(function () {
    $('#addCustomExtension').click(function () {
        let data = $('#extensionInput').val()

        let formData = {
            extension : data
        }

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/file/extension/add',
            data: JSON.stringify(formData),
            contentType: 'application/json',
            success: function (response) {
                console.log(response)
            },
            error: function (error) {
                console.log(error)
            }
        })
    })

});
