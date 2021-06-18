var express = require('express');
var router = express.Router();
var fs = require('fs');

/* GET home page. */

router.get('/:id', function(req, res, next) {
  
  res.status(200)
  var csvFile = fs.readFileSync("../../test/db.txt").toString().split("\n")
  console.log(csvFile[0])

  var i = Number.parseInt(req.params.id)
  
  res.jsonp(csvFile[i])
  res.end()
});

router.get('/', function(req, res, next) {
  res.status(200)
  var csvFile = fs.readFileSync("db.txt").toString().split("\n")

  res.send("Record Count : "+csvFile.length)
  res.end()
});

module.exports = router;
