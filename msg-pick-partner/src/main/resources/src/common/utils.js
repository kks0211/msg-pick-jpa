export const alertMessage = (message) => {
    Swal.fire({
        text: message,
        icon: "error",
        buttonsStyling: false,
        customClass: {
            confirmButton: "btn btn-danger"
        }
    });
}

export const successMessage = (message) => {
    return Swal.fire({
        text: message,
        icon: "success",
        buttonsStyling: false,
        confirmButtonText: "확인",
        customClass: {
            confirmButton: "btn btn-primary"
        }
    })
}

export const setComma = (value) => {
    const type = typeof value;
    if (type === 'string') {
        return Number(value.replace(/[^0-9]/g, "")).toLocaleString();
    }

    return value.toLocaleString();
};

export const removeComma = (value) => {
    const type = typeof value;
    if (type === 'string') {
        return value.replace(/[^0-9]/g, "");
    }

    return value;
}

export const initMaxLength = () => {
    const inputList = [].slice.call(document.querySelectorAll('input')).filter(item => item.type !== 'submit');
    const textAreaList = document.querySelectorAll('textarea');
    const elements = [...inputList, ...textAreaList];

    elements.forEach(item => {
        // maxlength 프로퍼티가 존재하면 maxlength 적용
        if (item.maxLength >= 1) {
            $(item).maxlength({
                warningClass: "badge badge-warning",
                limitReachedClass: "badge badge-success"
            });
        }
    });
}

export const serializeJSON = (form) => {
    const obj = {};
    [].slice.call(form.elements).forEach(function (field) {
        if (!field.name || field.disabled || ['file', 'reset', 'submit', 'button'].indexOf(field.type) > -1) return;
        if (['checkbox', 'radio'].indexOf(field.type) > -1 && !field.checked) return;
        if (field.type === 'select-multiple') {
            const options = [];
            [].slice.call(field.options).forEach(function (option) {
                if (!option.selected) return;
                options.push(option.value);
            });
            if (options.length) {
                obj[field.name] = options;
            }
            return;
        }
        if (field.type === 'checkbox') {
            if (!obj[field.name]) {
                obj[field.name] = [];
            }
            obj[field.name].push(field.value);
            return;
        }
        obj[field.name] = field.value;
    });

    return obj;
}
