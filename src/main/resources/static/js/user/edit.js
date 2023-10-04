function togglePasswordVisibility() {
    var passwordInput = document.getElementById('password');
    var passwordToggle = document.querySelector('.show-password-toggle');

    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        passwordToggle.textContent = '👁️';
    } else {
        passwordInput.type = 'password';
        passwordToggle.textContent = '👁️';
    }
}