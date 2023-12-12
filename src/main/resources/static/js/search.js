const searchForm = document.querySelector('#search-form');
const inputSearch = document.querySelector('.input-search');
const inputDate = document.querySelector('.input-date');
const dateLabel = document.querySelector('.date-label');
const inputPlace = document.querySelector('.input-place');
const selectAreaCon = document.querySelector('.place-select-con');
const btnAreaClose = document.querySelector('.btn-close-con');
const btnAreaSelectAll = document.querySelector('.btn-all');
const areaLists = document.querySelectorAll('.area-list > li');
const btnAreaCheck = document.querySelector('.btn-check');
const btnSearch = document.querySelector('.btn-search');

function handleAreaConClose() {
  selectAreaCon.classList.remove('show');
}

inputDate.addEventListener('change', () => {
  dateLabel.innerHTML = inputDate.value;
});

inputPlace.addEventListener('click', () => {
  selectAreaCon.classList.toggle('show');
});

btnAreaClose.addEventListener('click', () => {
  handleAreaConClose();
});

btnAreaSelectAll.addEventListener('click', () => {
  if (btnAreaSelectAll.classList.contains('all')) {
    areaLists.forEach(list => {
      list.classList.remove('area-click');
    });
  } else {
    areaLists.forEach(list => {
      list.classList.add('area-click');
    });
  }
  btnAreaSelectAll.classList.toggle('all');
});

areaLists.forEach(list => {
  list.addEventListener('click', () => {
    list.classList.toggle('area-click');
  });
});

btnAreaCheck.addEventListener('click', () => {
  const areaCheckLists = document.querySelectorAll('.area-click');
  let check = '';
  areaCheckLists.forEach((checkList, idx) => {
    if (idx == areaCheckLists.length - 1) {
      check += `${checkList.innerHTML}`;
      return;
    }
    check += `${checkList.innerHTML},`;
  });
  inputPlace.value = check;
  handleAreaConClose();
});

btnSearch.addEventListener('click', () => {
  if(inputSearch.value.length == 0) {
    alert('검색어를 입력해 주세요!');
    inputSearch.focus();
    return;
  }

  const areas =  document.querySelectorAll('.area-click');
  let areaList = "";
  if (areas.length != 0) {
    areas.forEach((area, idx) => {
      if (idx == areas.length - 1) {
        areaList += `${area.innerHTML}`;
        return;
      }
      areaList += `${area.innerHTML},`;
    })
  }

  const p = document.createElement('p');
  p.innerHTML = `<input type="text" name="area" value="${areaList}" hidden="hidden"/>`;

  searchForm.appendChild(p);

  searchForm.submit();
})
