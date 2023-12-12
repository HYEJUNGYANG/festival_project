const inputID = document.querySelector('.input-id');
const domainSel = document.querySelector('.select-email-domain');
const inputDomain = document.querySelector('.other-domains');
const btnDupli = document.querySelector('.btn-dupli');
const idWarning = document.querySelector('.id-warning');
const inputPw = document.querySelector('.input-pw');
const inputPwCheck = document.querySelector('.input-pw-check');
const pwWarning = document.querySelector('.pw-warning');
const pwChkWarning = document.querySelector('.pw-check-warning');
const pwShowBtn = document.querySelector('.pw-show');
const pwCheckShowBtn = document.querySelector('.pw-check-show');
const hiddenDate = document.querySelector('#hiddenDateField');
const inputYear = document.querySelector('.input-birth-year');
const inputMonth = document.querySelector('.input-birth-month');
const inputDay = document.querySelector('.input-birth-day');
const inputName = document.querySelector('.input-name');
const inputNick = document.querySelector('.input-nick');
const inputGender1 = document.querySelector('.input-gender1');
const inputGender2 = document.querySelector('.input-gender2');
const inputTel = document.querySelector('.input-tel');
const btnClose = document.querySelector('.btn-close');
const birthWarning = document.querySelector('.birth-warning');
const tot_war = document.querySelector('.total-warning');
const id_pattern = /^[0-9a-z]$/;
function handlePwShow(element) {  //
  const input = element.previousElementSibling;
  input.focus();
  if (input.type == 'password') {
    input.type = 'text';
    element.firstElementChild.classList.replace('fa-eye', 'fa-eye-slash');
  } else {
    input.type = 'password';
    element.firstElementChild.classList.replace('fa-eye-slash', 'fa-eye');
  }
}

domainSel.addEventListener('change', () => {
  if (domainSel.options[domainSel.selectedIndex].value == 'otherDomains') {
    domainSel.classList.add('other');
    inputDomain.classList.add('show');
  }
});

pwShowBtn.addEventListener('click', () => {
  handlePwShow(pwShowBtn);
});

pwCheckShowBtn.addEventListener('click', () => {
  handlePwShow(pwCheckShowBtn);
});

btnClose.addEventListener('click', () => {
  if (
      confirm('작성중인 내용을 모두 잃게됩니다. 이전 페이지로 이동하시겠습니까?')
  ) {
    location.replace(document.referrer);
  }
});

// 전화번호 하이픈 표시
inputTel.addEventListener('input', () => {
  inputTel.value = inputTel.value
      .replace(/[^0-9]/g, '')
      .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
});

const xhr = new XMLHttpRequest();

let isIdCheck = false;
// id 중복 체크 버튼 클릭시
btnDupli.addEventListener('click', () => {
  if (inputID.value.length == 0 || domainSel.options[domainSel.selectedIndex].value == 'none' || (inputDomain.classList.contains('show') && inputDomain.value.length == 0) || id_pattern.test(inputDomain.value)) {
    alert('이메일을 입력해주세요!!');
    return;
  }

  idWarning.style.visibility = "hidden";
  let id = "";
  if(domainSel.options[domainSel.selectedIndex].value != "otherDomains") {
    id = `${inputID.value}@${domainSel.options[domainSel.selectedIndex].value}`;
  }
  else{
    id = `${inputID.value}@${inputDomain.value}`;
  }
  xhr.open('POST', '/join/check_id', true);
  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xhr.send(`id=${id}`);
  xhr.onreadystatechange = function () {
    if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
      if (xhr.response) {
        alert('중복된 아이디가 존재합니다!!');
        idWarning.innerHTML = '중복된 아이디가 존재합니다!!';
      }
      else { //추후 수정 필요함
        if(inputDomain.classList.contains('show') == false) {
          if (confirm(`사용 가능한 아이디 입니다!!👋🏻 [${inputID.value}@${domainSel.options[domainSel.selectedIndex].value}] 이 아이디를 사용하시겠습니까?`)) {
            inputID.disabled = true;
            domainSel.disabled = true;
            isIdCheck = true;
          }
        }
        else{
          if (confirm(`사용 가능한 아이디 입니다!!👋🏻 [${inputID.value}@${inputDomain.value}] 이 아이디를 사용하시겠습니까?`)) {
            inputID.disabled = true;
            domainSel.disabled = true;
            isIdCheck = true;
          }
        }
        idWarning.innerHTML = '';
      }
    }

    else if (xhr.status == 500){
      alert('실패하였습니다!');
    }
  };
});


/*
* 여기서 부터는 원종이 코드
* */

<!--=======================================-->
<!--===============id 패턴에 맞추면 경고문 사라짐 start===============-->
<!--=======================================-->

document.querySelector(".input-id").addEventListener("keyup", function() {
  // 입력한 아이디를 가져옵니다.
  var id = this.value;

  // 아이디를 입력하면 경고를 없앱니다.
  if(id != ""){
    idWarning.style.visibility = "hidden";
  }
  // 아이디가 공백이면 경고를 띄웁니다.
  else if(id == ""){
    idWarning.style.visibility = "visible";
  }
});
document.querySelector(".other-domains").addEventListener("keyup", function() {

  var email = domainSel.value;

  var email_other = inputDomain.value;

  if (email == "" || email == "otherDomains") { //email 공백이면
    if (email_other == "") {
      idWarning.style.visibility = "visible";
      idWarning.innerText = "이메일을 입력해 주세요.";
    }else{
      idWarning.style.visibility = "hidden";
    }
  }else{
    idWarning.style.visibility = "hidden";
  }

});

document.querySelector(".select-email-domain").addEventListener('change', function(event) {
  var email_other = inputDomain.value;
  var email = domainSel.value;

  if (email == "" || email == "otherDomains") { //email 공백이면
    if (email_other == "") {
      idWarning.style.visibility = "visible";
      idWarning.innerText = "이메일을 입력해 주세요.";
    }else{
      idWarning.style.visibility = "hidden";
    }
  }else{
    idWarning.style.visibility = "hidden";
  }

});
<!--=======================================-->
<!--===============id 패턴에 맞추면 경고문 사라짐 end===============-->
<!--=======================================-->

<!--=======================================-->
<!--===============pw 패턴에 맞추면 경고문 사라짐 start===============-->
<!--=======================================-->
inputPw.addEventListener("keyup", function() {
  // 비밀번호 패턴 정규식
  var pwPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;

  // 입력한 비밀번호를 가져옵니다.
  var pw = this.value;

  // 입력한 비밀번호가 패턴에 맞지 않으면 경고 메시지를 보이게 하고, 그렇지 않으면 숨깁니다.
  if (pwPattern.test(pw)) {
    pwWarning.style.visibility = "hidden";
  }
});
<!--=======================================-->
<!--===============pw 패턴에 맞추면 경고문 사라짐 end===============-->
<!--=======================================-->

<!--=======================================-->
<!--===============pw chk 실시간 반영 start===============-->
<!--=======================================-->
inputPwCheck.addEventListener("keyup", function() {
  // 비밀번호를 가져옵니다.
  var pw = inputPw.value;
  // 비밀번호 확인 값.
  var pw_chk = this.value;
  // 비밀번호 경고 메시지 DOM을 가져옵니다.

  // 입력한 비밀번호가 패턴에 맞지 않으면 경고 메시지를 보이게 하고, 그렇지 않으면 숨깁니다.
  if (pw_chk == pw) {
    pwChkWarning.style.visibility = "hidden";
  }
  else{
    pwChkWarning.style.visibility = "visible";
    pwChkWarning.innerText = "입력한 비밀번호와 똑같이 적어주세요.";
  }
});
<!--=======================================-->
<!--===============pw chk 실시간 반영 end===============-->
<!--=======================================-->

<!--=======================================-->
<!--===============birth chk 실시간 반영 start===============-->
<!--=======================================-->

var birthes = document.querySelectorAll('.input-birth-year, .input-birth-month');
birthes.forEach(function(element) {
  element.addEventListener('change', function(event) {
    if (element.value == "") {
      birthWarning.style.visibility = "visible";
      birthWarning.innerText = "입력되지 않은 칸이 존재합니다.";
    }
    else{
      birthWarning.style.visibility = "hidden";
    }
  });
});
document.querySelector(".input-birth-day").addEventListener("keyup", function() {
  // 비밀번호를 가져옵니다.
  var day = document.querySelector(".input-birth-day").value;
  // 비밀번호 경고 메시지 DOM을 가져옵니다.

  // 입력한 비밀번호가 패턴에 맞지 않으면 경고 메시지를 보이게 하고, 그렇지 않으면 숨깁니다.
  if (day != "") {
    birthWarning.style.visibility = "hidden";
  }
  else{
    birthWarning.style.visibility = "visible";
    birthWarning.innerText = "입력되지 않은 칸이 존재합니다.";
  }
});
<!--=======================================-->
<!--===============birth chk 실시간 반영 end===============-->
<!--=======================================-->
<!--=======================================-->
<!--===============total chk 실시간 반영 start===============-->
<!--=======================================-->
var total = document.querySelectorAll('.input-name, .input-nick, .input-gender1, .input-gender2, .input-tel');
total.forEach(function(element) {
  element.addEventListener('keyup', function() {
    if (element.value == "") {
      tot_war.style.visibility = "visible";
      tot_war.innerText = "입력되지 않은 칸이 존재합니다.";
    }
    else{
      tot_war.style.visibility = "hidden";
    }
  });
});
<!--=======================================-->
<!--===============total chk 실시간 반영 end===============-->
<!--=======================================-->

document.querySelector("#joinform").addEventListener("submit", function(event) {
  event.preventDefault(); // form의 기본 제출 동작을 취소합니다.
  <!--===============id+email 합치기+id check start===============-->
  var id = inputID.value;

  var email = domainSel.value;

  var email_other = inputDomain.value;

  if (id == "") { //id 입력안했을때
    idWarning.style.visibility = "visible";
    inputID.focus();

    return false;
  }

  if (email == "" || email == "otherDomains") { //email 공백이면
    if(email_other == ""){
      idWarning.style.visibility = "visible";
      idWarning.innerText = "이메일을 입력해 주세요.";
      inputID.focus();

      return false;
    }

  }

  //아이디+이메일 합치기
  if(email == "otherDomains") {
    var u_id = id + "@" + email_other;
    console.log(u_id);
  }
  else{
    var u_id = id + "@" + email;
    console.log(u_id);
  }

  document.querySelector("#hiddenIdField").value = u_id;

  <!--===============id+email 합치기+id check end===============-->
  <!--===============pw check start===============-->
  var pw = inputPw.value;

  var pw_chk = inputPwCheck.value;

  // 비밀번호 패턴 정규식
  var pwPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;

  if (!pwPattern.test(pw)) {
    pwWarning.style.visibility = "visible";
    pwWarning.innerText = "비밀번호는 영어, 숫자, 특수문자 조합 8-16자여야 합니다.";
    pwWarning.focus();

    return false;
  }

  if (pw != pw_chk) {
    inputPwCheck.focus();
    pwChkWarning.style.visibility = "visible";
    pwChkWarning.innerText = "입력한 비밀번호와 똑같이 적어주세요.";
    return false;
  }
  <!--=============== pw check end ===============-->

  <!--===============name,nick check start===============-->
  var name = inputName.value;

  var nick = inputNick.value;

  if (name == "" || nick == "") {
    tot_war.style.visibility = "visible";
    tot_war.innerText = "입력되지 않은 칸이 존재합니다.";

    return false;
  }

  <!--===============name,nick check end===============-->

  <!--===============birth date type으로 바꾸기+check start===============-->

  var year = inputYear.value;

  var month = inputMonth.value;

  var day = inputDay.value;

  var birthPattern = /^[0-9]+$/;

  var currentYear = new Date().getFullYear(); // 현재 연도를 가져옵니다.

  var currentMonth = new Date().getMonth(); // 현재 월을 가져옵니다.

  var currentDay = new Date().getDate(); // 현재 일을 가져옵니다.

  month = month.padStart(2, '0');
  day = day.padStart(2, '0');
  var dateString = year + '-' + month + '-' + day;

  if (year > currentYear || !birthPattern.test(year)) {
    birthWarning.style.visibility = "visible";
    birthWarning.innerText = "연도가 잘못되었습니다. 다시 입력해주세요.";

    return false;
  }
  else{
    if (month > currentMonth+1) {
      birthWarning.style.visibility = "visible";
      birthWarning.innerText = "월이 잘못 되었습니다. 다시 입력해주세요.";

      return false;
    }else if(month == currentMonth+1){
      if (day > currentDay || !birthPattern.test(day)) {
        birthWarning.style.visibility = "visible";
        birthWarning.innerText = "일이 잘못 되었습니다. 다시 입력해주세요.";

        return false;
      }
    }else {
      if (day > 31 || !birthPattern.test(day)) {
        birthWarning.style.visibility = "visible";
        birthWarning.innerText = "일이 잘못 되었습니다. 다시 입력해주세요.";

        return false;
      }
    }
  }
  hiddenDate.value = dateString;

  <!--===============birth date type으로 바꾸기 end===============-->

  <!--===============gender,tel check start===============-->
  var gender1 = inputGender1.value;

  var gender2 = inputGender2.value;

  var tel = inputTel.value;

  if (gender1 == "" || gender2 == "" || tel == "" ) {
    tot_war.style.visibility = "visible";
    tot_war.innerText = "입력되지 않은 칸이 존재합니다.";

    return false;
  }
  <!--===============gender,tel check end===============-->
  // 최종적으로 아이디 중복확인 체크 했는지 체크
  if (!isIdCheck) {
    alert('아이디 중복 확인이 필요합니다!');
    return;
  }
  // form을 제출합니다.
  inputID.disabled = false;
  domainSel.disabled = false;
  event.target.submit();
});