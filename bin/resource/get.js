//codes.js     
system = require('system')     
address = system.args[1];//获得命令行第二个参数 接下来会用到     
var page = require('webpage').create();     
var url = address;     
page.open(url, function (status) {     
    //Page is loaded!     
    if (status !== 'success') {     
        console.log('Unable to post!');     
    } else {     
    page.evaluate(function(){
        window.scrollTo(0,10000);//滚动到底部
    });
	window.setTimeout(function () {
            if(page.loadingProgress == 100){
                console.log(page.content);
            }
            phantom.exit();
        }, 
        500);   
    }        
});