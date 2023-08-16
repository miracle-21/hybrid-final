/**
 * 
 */

function setCookie(name, value, expireDay) {
	var today = new Date();
	today.setDate(today.getDate() + expireDay);
	document.cookie = name + '=' + escape(value) + '; path=/; expires='
		+ today.toGMTString() + ';';
}
function getCookie(name) {
	var cook = document.cookie + ';';
	var idx = cook.indexOf(name, 0);
	var val = '';
	if (idx != -1) {
		cook = cook.substring(idx, cook.length);
		begin = cook.indexOf('=', 0) + 1;
		end = cook.indexOf(';', begin);
		val = unescape(cook.substring(begin, end));
	}
	return val;
}