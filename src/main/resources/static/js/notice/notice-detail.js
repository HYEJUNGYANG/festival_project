const scrollBtn = document.querySelector('.scroll');

addEventListener('scroll', () => {
  const y = scrollY;
  if (y > 150) {
    scrollBtn.classList.add('show');
  } else {
    scrollBtn.classList.remove('show');
  }
});

scrollBtn.addEventListener('click', () => {
  scrollTo({ top: 0, behavior: 'smooth' });
});
