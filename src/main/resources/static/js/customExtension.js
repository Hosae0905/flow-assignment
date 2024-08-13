$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/file/custom/extension/list',
        success: function (response) {
            console.log(response)
            let customExtensionList = $('#customExtensionList')
            response.forEach(function (item) {
                const extension = item.extension
                let extensionTag = $(`
                    <span>${extension}
                        <button class="remove-btn">X</button>
                    </span>
                `)

                extensionTag.find('.remove-btn').click(function () {
                    extensionTag.remove()
                });
                customExtensionList.append(extensionTag)

            });
        },
        error: function (error) {
            console.log(error)
        }
    })
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
