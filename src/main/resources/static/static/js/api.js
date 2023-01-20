function askVerifyCode() {
    $.get('/api/auth/verify-code', {
        email: $("#input-email").val()
    })
}