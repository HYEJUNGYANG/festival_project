const header = document.querySelector('.header');
const btnProfile = document.querySelector('.user-profile');
const myPage = document.querySelector('.my-page');

let isClick = false;

addEventListener('load', () => {
  checkScrollPosition();
})

function checkScrollPosition() {
  const y = scrollY;

  if (y > 5) {
    header.classList.add('scroll');
  } else if (y <= 5 && !isClick) {
    header.classList.remove('scroll');
  }
}

addEventListener('scroll', () => {
  checkScrollPosition();
});

if (btnProfile) {
  btnProfile.addEventListener('click', () => {
    header.classList.add('scroll');
    myPage.classList.toggle('show');
    isClick = !isClick;
    if (!isClick && scrollY <= 5) {
      header.classList.remove('scroll');
    }
  });
}
