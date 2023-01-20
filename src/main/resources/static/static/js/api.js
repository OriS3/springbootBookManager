function askVerifyCode() {
    $.get('/api/auth/verify-code', {
        email: $("#input-email").val()
    })
}

function login() {
    $.post('/api/auth/login', {
        username: $("#username").val(),
        password: $("#password").val()
    }, function (data){
        if (data.code == 200) {
            window.location = "/index.html"
        } else {
            alert("登录失败, 用户名或密码错误");
        }
    })
}