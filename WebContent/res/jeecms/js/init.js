/**
 * 初始化
 */
$(function(){
	//给ie8添加indexOf方法
	if(!Array.indexOf){
		Array.prototype.indexOf=function(obj){
			for(var i=0;i<this.length;i++){
				if(this[i]==obj){
					return i;
				}
			}
			return -1;
		}
	}
});