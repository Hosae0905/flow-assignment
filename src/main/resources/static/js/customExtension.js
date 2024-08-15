$(function getCustomExtensionList () {
    let option = 'custom'
    const maxCustomExtensionSize = 200
    $.ajax({
        type: 'GET',
        url: `http://localhost:8080/file/custom/extension/list?option=${option}`,
        success: function (response) {
            if (response.code === 'CUSTOM_002') {
                let customExtensionList = $('#customExtensionList');
                let totalCustomExtension = $('#totalCustomExtension')

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
                totalCustomExtension.append(`${response.result.length}/${maxCustomExtensionSize}`)
            }
        },
        error: function (error) {
            if (error.responseJSON.code === 'EXTENSION_ERROR_002') {
                alert(error.responseJSON.message)
            } else if (error.code === 'SERVER_ERROR_001') {
                alert(error.responseJSON.message)
            }
        }
    })
    $('#addCustomExtension').click(function addCustomExtension () {
        let data = $('#extensionInput').val()
        if (data.length <= 20) {
            let formData = {
                extension : data,
                option: option
            };
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/file/extension/add',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (response) {
                    if (response.code === 'CUSTOM_001') {
                        alert(response.message)
                        $('#extensionInput').val('')
                    }
                },
                error: function (error) {
                    if (error.responseJSON.code === 'EXTENSION_ERROR_002' || error.responseJSON.code === 'EXTENSION_ERROR_003') {
                        alert(error.responseJSON.message)
                        $('#extensionInput').val('')

                    } else if (error.code === 'SERVER_ERROR_001') {
                        alert(error.responseJSON.message)
                    }
                }
            })
        } else {
            alert('커스텀 확장자의 길이는 최대 20자까지 가능합니다.')
        }
    })
});

$(document).on('click', 'button[name="removeExtensionBtn"]', function delCustomExtension () {
    let option = 'custom'
    let formData = {
        extension: $(this).siblings('label').text().trim(),
        option: option
    }

    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/file/custom/extension/remove',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function (response) {
            if (response.code === 'CUSTOM_003') {
                alert(response.message)
            }
        },
        error: function (error) {
            if (error.responseJSON.code === 'EXTENSION_ERROR_001' || error.responseJSON.code === 'EXTENSION_ERROR_002') {
                alert(error.responseJSON.message)
            } else if (error.code === 'SERVER_ERROR_001') {
                alert(error.responseJSON.message)
            }
        },
    })
});
