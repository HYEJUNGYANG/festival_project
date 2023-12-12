const btnModifys = document.querySelectorAll('.btn-modify');
const btnDeletes = document.querySelectorAll('.btn-del');

btnModifys.forEach(btnModify => {
  btnModify.addEventListener('click', () => {
    const width = window.screen.width;
    const height = window.screen.height;

    const popWidth = 650;
    const popHeight = 500;
    open(
        `/mypage/review/modify?idx=${btnModify.dataset.idx}`,
      'review',
      `resizable=no width=${popWidth}, height=${popHeight} top=${
        height / 2 - popHeight / 2
      } left=${width / 2 - popWidth / 2}`
    );
  });
});

btnDeletes.forEach((btnDelete, idx) => {
  btnDelete.addEventListener('click', () => {

    const idx = btnDelete.dataset.idx;
    if (confirm('정말 삭제하시겠습니까?')) {
      location.href = `/mypage/review/delete?idx=${idx}`;
    }
  })
});