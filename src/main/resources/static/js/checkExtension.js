$(document).ready(function () {
    $('#myCheckbox').change(function () {
        if (this.checked) {
            let formData = {
                extension: $(this).val()
            };

            console.log(formData)

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
        }
    })
})
