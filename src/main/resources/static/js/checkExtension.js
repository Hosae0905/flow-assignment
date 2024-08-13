$(document).on('change', function () {
    $('input[type="checkbox"][name="extension"]').on('change', function () {
        let formData = {
            extension: $(this).val()
        };
        if (this.checked) {
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/file/extension/checked',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (response) {
                    console.log(response)
                },
                error: function (error) {
                    console.log(error)
                }
            })
        } else {
            console.log(formData)
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/file/extension/unchecked',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (response) {
                    console.log(response)
                },
                error: function (error) {
                    console.log(error)
                }
            })
        }
    })
})


