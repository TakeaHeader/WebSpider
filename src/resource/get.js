system = require('system')     
address = system.args[1];     
var page = require('webpage').create();     
var url = address;     
page.open(url, function (status) {     
    if (status !== 'success') {     
        console.log('Unable to post!');     
    } else {     
    page.evaluate(function(){
        window.scrollTo(0,10000);
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