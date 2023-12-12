const btnMypageRe = document.querySelector('.btn-mypage-re');

// 나중에는 유저 값 받아와서 그에 맞는 링크로 이동할 수 있도록 수정할 것!!
btnMypageRe.addEventListener('click', () => {
  location.href = '/mypage/reservation';
});
