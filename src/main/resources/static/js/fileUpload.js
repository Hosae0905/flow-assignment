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
                if (response === false) {
                    alert('현재 확장자의 파일은 업로드할 수 없습니다. 다른 파일로 시도해주세요')
                }
            },
            error: function (error) {
                console.log(error)
            }
        })
    })
})
