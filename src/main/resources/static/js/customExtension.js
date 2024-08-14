$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/file/custom/extension/list',
        success: function (response) {
            console.log(response)
            let customExtensionList = $('#customExtensionList')
            response.result.forEach(function (item, index) {
                const customExtension = item.extension
                const customExtensionIdx = index

                let extensionTag = $(`
                    <div class="custom-extension-item">
                        <label for="${customExtensionIdx}" id="customExtension">${customExtension}</label>
                        <button id="${customExtensionIdx}" class="remove-btn" name="removeExtensionBtn" aria-label="test">X</button>
                    </div>
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
        if (data.length <= 20) {
            let formData = {
                extension : data
            };
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
        } else {
            alert('커스텀 확장자의 길이는 최대 20자까지 가능합니다.')
        }
    })
});

$(document).on('click', 'button[name="removeExtensionBtn"]', function () {
    let formData = {
        customExtension: $(this).siblings('label').text().trim()
    }

    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/file/custom/extension/remove',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function (response) {
            console.log(response)
        },
        error: function (error) {
            console.log(error)
        },
    })
});
