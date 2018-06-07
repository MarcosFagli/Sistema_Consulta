/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function monitorarAjax(data) {
    var ajaxStatus = data.status;
    var ajaxLoader = document.getElementById("ajaxLoader");


    switch (ajaxStatus) {
        case "begin":
            ajaxLoader.style.display = 'block';
            break;


        case "complete":
            ajaxLoader.style.display = 'none';
            break;
    }
}


