$(document).ready(function () {
    $('#myCheckbox').change(function () {
        if (this.checked) {
            let formData = {
                type: $(this).val()
            };

            $.ajax({
                type: 'POST',
                url: 'http://localhost:7070/upload/check',
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
