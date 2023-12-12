const btnCancel = document.querySelector('.btn-cancel');
const btnClose = document.querySelector('.btn-close');

addEventListener('keydown', e => {
  const key = e.key;
  if (key == 'Escape') {
    window.close();
  }
});

if (btnCancel != null) {
  btnCancel.addEventListener('click', () => {
    const num = btnCancel.dataset.num;
    if (confirm('취소요청 하시겠습니까?')) {
      location.href = `/mypage/reservation/cancel?num=${num}`;
    }
  })
}

btnClose.addEventListener('click', () => {
  window.close();
});
