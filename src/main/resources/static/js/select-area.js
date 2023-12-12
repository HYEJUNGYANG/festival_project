const url = new URL(location.href);
// URLSearchParams 객체
const urlParams = url.searchParams;

// URLSearchParams.get()
const zone = (urlParams.get('zone'));

const areaBtn = document.querySelectorAll('.area');

let areaCount = 0;

areaBtn.forEach((area, idx) => {
  if (area.innerHTML == zone) {
    area.classList.add('sel');
  }
});
