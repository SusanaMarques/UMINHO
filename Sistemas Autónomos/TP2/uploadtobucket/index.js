const { exec } = require("child_process");
const fs = require('fs');



function upload(){
exec("gsutil cp SATP2/test/db.csv gs://trafficflow_bucket/", (error, stdout, stderr) => {
    if (error) {
        console.log(`error: ${error.message}`);
        return;
    }
    if (stderr) {
        console.log(`stderr: ${stderr}`);
        return;
    }
    console.log(`stdout: ${stdout}`);
});
}
var interval = 2*60*60*1000 //2 em 2 horas
setInterval( upload, interval);
upload()