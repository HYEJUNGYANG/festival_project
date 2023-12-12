const form = document.querySelector('#modify_form');
const txtDetail = document.querySelector('#txt_detail');
const txtContent = document.querySelector('#txt_content');
const txtWarning = document.querySelector('#txt_warning');
const btnSubmit = document.querySelector('#btn-submit');

let editorD;
let editorC;
let editorW;

ClassicEditor.create(document.querySelector('#editor_detail'), {
    language: 'ko',
    toolbar: ['heading', '|', 'bold', 'italic']
})
    .then(newEditor => {
        editorD = newEditor;
    })
    .catch(error => {
        console.error(error);
    });
ClassicEditor.create(document.querySelector('#editor_content'), {
    language: 'ko',
    toolbar: ['heading', '|', 'bold', 'italic']
})
    .then(newEditor => {
        editorC = newEditor;
    })
    .catch(error => {
        console.error(error);
    });
ClassicEditor.create(document.querySelector('#editor_warning'), {
    language: 'ko',
    toolbar: ['heading', '|', 'bold', 'italic']
})
    .then(newEditor => {
        editorW = newEditor;
    })
    .catch(error => {
        console.error(error);
    });

document.getElementById('fileUpload').addEventListener('change', function() {
    var fileName = this.files[0].name;
    document.getElementById('fileName').innerHTML = fileName;
});

btnSubmit.addEventListener('click', () => {
    txtDetail.value = editorD.getData();
    txtContent.value = editorC.getData();
    txtWarning.value = editorW.getData();

    const inputTag = document.querySelector('#input-tag');
    const hideinputTag = document.querySelector('#hide-input-tag');
    let tag = inputTag.value;

    let db_tag = tag.replace(/#/g, '@@');
    db_tag_in = '@@' + db_tag.substring(2) + '@@';

    hideinputTag.value = db_tag_in;

    form.submit();
})