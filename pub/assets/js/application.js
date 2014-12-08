$(document).ready(function() {
  var ie = window.ActiveXObject || "ActiveXObject" in window;
  if (ie) {
    // fix modal popups so that they display in IE
    jQuery('.modal').removeClass('fade');
    // add table striping to tables in IE
    jQuery('.table-striped>tbody>tr:even>td,.table-striped>tbody>tr:even>th').css('background-color','#f0f0e0');
    jQuery('.table-striped>tbody>tr:odd>td,.table-striped>tbody>tr:odd>th').css('background-color','#e0e0c0');
  }
});