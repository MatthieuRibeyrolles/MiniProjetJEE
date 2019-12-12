$(document).ready(function() {
    var info_name = $("#info_name"),
        info_ref = $("#info_ref");

    $(".product").click( function() {
        let name = $(this).attr('id');
        
        info_name.text("Vous consultez actuellement le produit " + name);
        info_ref.style.visibility = 'visible';
    });
});