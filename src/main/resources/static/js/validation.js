//유효성 검사 JS

// 공백 검사 함수
function isFirstCharSpace(str) {
    return /^\s/.test(str)
}

// 특수문자 검사 [!@#$%]
function hasSpecialChar(str) {
    return /[!@#$%^&*():{}|<>,'~_=\.\-\\`]/.test(str);
}

document.getElementById('categoryInputForm').addEventListener('submit', function(e) {

    //각 아이디요소를 변수로 정의
    const startWithSpaceError = document.getElementById('startWithSpaceError');
    const specialCharError = document.getElementById('specialCharError');
    const categoryInput = document.getElementById('categoryInput');
    const value = categoryInput.value;

    //에러 정의(불리언값)
    const isSpaceError = isFirstCharSpace(value);
    const isSpecialCharError = hasSpecialChar(value);

    //유효성검사 함수 삼항연산자 에러 표시
    startWithSpaceError.style.display = isSpaceError ? 'block' : 'none';
    specialCharError.style.display = isSpecialCharError ? 'block' : 'none';

    //유효성검사 통과여부에 따른 문법
    if (isSpaceError || isSpecialCharError) {
        categoryInput.classList.add('warning-border');
        e.preventDefault();  // 유효성 검사 실패 시 서밋안하기
    } else {
        categoryInput.classList.remove('warning-border');
    }

})
