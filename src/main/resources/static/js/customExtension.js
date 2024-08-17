const maxCustomExtensionSize = 200
function loadCustomExtensionList() {
    let option = 'custom'
    $.ajax({
        type: 'GET',
        url: `http://localhost:8080/extension/custom/list?option=${option}`,
        success: function (response) {
            if (response.code === 'CUSTOM_002') {
                let customExtensionList = $('#customExtensionList')
                let totalCustomExtension = $('#totalCustomExtension')
                let currentCount = response.result.length;
                customExtensionList.empty()
                totalCustomExtension.empty()

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

                totalCustomExtension.append(`${currentCount}/${maxCustomExtensionSize}`)
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
}

$(function getCustomExtensionList () {
    loadCustomExtensionList()
    let option = 'custom'
    let currentCount = 0;
    $('#addCustomExtension').click(function addCustomExtension () {
        let data = $('#extensionInput').val()
        $.ajax({
            type: 'GET',
            url: `http://localhost:8080/extension/custom/list?option=${option}`,
            success: function (response) {
                currentCount = response.result.length
            },
            error: function (error) {
                if (error.responseJSON.code === 'EXTENSION_ERROR_002') {
                    alert(error.responseJSON.message)
                } else if (error.code === 'SERVER_ERROR_001') {
                    alert(error.responseJSON.message)
                }
            },
        })
        if (currentCount >= maxCustomExtensionSize) {
            alert('커스텀 확장자는 최대 200개까지만 가능합니다.')
            return;
        }

        let regResult = new RegExp('^[a-z]+$').test(data)
        if (!regResult) {
            alert('확장자는 영어 소문자로만 작성해주세요.')
            $('#extensionInput').val('')
            return;
        }

        if (data.length <= 20) {
            let formData = {
                extension : data,
                option: option
            };
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/extension/custom/add',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (response) {
                    if (response.code === 'CUSTOM_001') {
                        alert(response.message)
                        $('#extensionInput').val('')
                        loadCustomExtensionList()
                    }
                },
                error: function (error) {
                    if (error.responseJSON.code === 'EXTENSION_ERROR_002' || error.responseJSON.code === 'EXTENSION_ERROR_003') {
                        alert(error.responseJSON.message)
                        $('#extensionInput').val('')

                    } else if (error.code === 'SERVER_ERROR_001') {
                        alert(error.responseJSON.message)
                    }
                },
            });
        } else {
            alert('커스텀 확장자의 길이는 최대 20자까지 가능합니다.');
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
        url: 'http://localhost:8080/extension/custom/remove',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function (response) {
            if (response.code === 'CUSTOM_003') {
                alert(response.message)
                loadCustomExtensionList()
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
