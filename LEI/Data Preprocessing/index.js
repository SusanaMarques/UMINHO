fs = require("fs")

var file = fs.readFileSync("./Data/dataset.csv").toString();
var hashTab=[]
var array =[]
array = file.split(/\r?\n/);
for(i=0;i<array.length;i++){

line = array[i];
var entry={key:"",values:[]}


var allSymps=[];
var lines =line.split(',')
for(j=0;j<lines.length;j++){
    
    element= lines[j];
    if(j==0) {
        entry.key=element
    }
        else {
            if(element!=="") entry.values.push(element)
        }
}
var inhash =hashTab.find(el=>el.key==entry.key )
if(inhash)
    Array.prototype.push.apply(inhash.values, entry.values)
else{
    hashTab.push(entry)
}

}

for(i=1;i<hashTab.length;i++){
    uniq = [...new Set([...allSymps,...hashTab[i].values])];
    allSymps= uniq;
}

var newCSV =[]
var newLine=["Disease",...allSymps]
newCSV.push(newLine.join(','))
const header = newLine;
newLine=[];
console.log(allSymps)
for(i=0;i<allSymps.length;i++){
    newLine.push(0);
}

const freshLn= newLine;
for(i=1;i<array.length;i++){
    var lines =array[i].split(',')
    newLine=[lines[0],...freshLn];
    
    
    for(j=1;j<lines.length;j++){
        element= lines[j];
        var inn=header.indexOf(element);
        newLine[inn]=1;
    }
    newCSV.push(newLine.join(','));
    
}
fs.writeFileSync("./Data/processed.csv", newCSV.join('\n'));




/*
for(i=0;i<hashTab.length;i++){
    uniq = [...new Set(hashTab[i].values + allSymps )];
    allSymps= uniq;
    hashTab[i].values=uniq;
}

*/
//console.log(hashTab)
//console.log(allSymps)
console.log("DON")