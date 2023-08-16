/**
 * 
 */
// 널값 및 데이터 없음 체크.
function isEmpty(arg){
	if(arg == "" || arg == null || arg == undefined || 
		(arg != null && typeof arg == "object" && !Object.keys(arg).length)){
		return true;
	}
	return false;
}

// 문자열에 공백이 있는지 체크.
function haveBlank(arg){
	var pattern = /\s/g; // 스페이스, 탭 체크.
	if(arg.match(pattern)){
		return true;
	}
	return false;
}

function inLength(arg, min, max){
	if(arg.length < min || arg.length > max){
		return false;
	}
	return true;
}