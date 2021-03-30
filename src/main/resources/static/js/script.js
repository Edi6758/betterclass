const showModalDeletar = function(id) {
    $('#modalDeletar').modal('show')
    $('#id_deletar').val(id)
}

const deletar = function(path) {
    let id = $('#id_deletar').val()
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
$(document).ready(function () {
    $(".link-view").on("click", function (e) {
        e.preventDefault();
        $("#modalView").modal("show").find(".modal-content").load($(this).attr("href"))
    })
})
$(document).ready(function () {
    $(".link-edit").on("click", function (e) {
        e.preventDefault();
        $("#modalView").modal("show").find(".modal-content").load($(this).attr("href"))
    })
})



$(document).ready(function () {
    $("#pesquisa").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $(".col").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

function segunda() {
    $("#btn-segunda")
    var valor = document.getElementById("btn-segunda").value;
    $(".col").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(valor) > -1)
    })
}
function terca() {
    $("#btn-terca")
    var valor = document.getElementById("btn-terca").value;
    $(".col").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(valor) > -1)
    })
}
function quarta() {
    $("#btn-quarta")
    var valor = document.getElementById("btn-quarta").value;
    $(".col").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(valor) > -1)
    })
}
function quinta() {
    $("#btn-quinta")
    var valor = document.getElementById("btn-quinta").value;
    $(".col").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(valor) > -1)
    })
}
function sexta() {
    $("#btn-sexta")
    var valor = document.getElementById("btn-sexta").value;
    $(".col").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(valor) > -1)
    })
}
function sabado() {
    $("#btn-sabado")
    var valor = document.getElementById("btn-sabado").value;
    $(".col").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(valor) > -1)
    })
}