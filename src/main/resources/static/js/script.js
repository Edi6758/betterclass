const showModalDeletar = function(id) {
    $('#modalDeletar').modal('show')
    $('#id_deletar').val(id)
}

const deletar = function(path) {
    let id = $('#id_deletar').val()
    console.log("edi" + id)
    if (id !== 0) {
        window.location.href = '/' + path + '/delete/' + id
    }
}

//----------------THUMBNAIL IMAGEM INPUT----------------------

$(document).ready(function(){

    $('#fileImage').change(function(){
        showImageThumbnail(this);
    })

});

function showImageThumbnail(fileInput){
    file = fileInput.files[0];
    reader = new FileReader();

    reader.onload = function(e){
        $('#thumbnail').attr('src', e.target.result);
    };

    reader.readAsDataURL(file);
}