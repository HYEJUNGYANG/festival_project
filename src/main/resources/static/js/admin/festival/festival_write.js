const form = document.querySelector('#write_form');
const txtContent = document.querySelector('#txt_detail');
const btnSubmit = document.querySelector('#btn-submit');

let editor;

ClassicEditor.create(document.querySelector('#editor'), {
    language: 'ko',
    toolbar: ['heading', '|', 'bold', 'italic']
})
    .then(newEditor => {
        editor = newEditor;
    })
    .catch(error => {
        console.error(error);
    });

    document.getElementById('fileUpload').addEventListener('change', function() {
                var fileName = this.files[0].name;
                document.getElementById('fileName').innerHTML = fileName;
            });

btnSubmit.addEventListener('click', () => {
    txtContent.value = editor.getData();

    const inputTag = document.querySelector('#input-tag');
    const hideinputTag = document.querySelector('#hide-input-tag');
    let tag = inputTag.value;

    let db_tag = tag.replace(/#/g, '@@');
    db_tag_in = '@@' + db_tag.substring(2) + '@@';

    hideinputTag.value = db_tag_in;

    form.submit();
})
