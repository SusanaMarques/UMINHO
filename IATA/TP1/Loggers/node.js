const ioHook = require('iohook');
const express = require('express');
const app = express();

let startup = Date.now();
let data = {};
let contadores = [];

/* API Endpoint */

app.use(function(req, res, next) {

  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();

});

app.get('/', (req, res) => {

  data.general = {

    runtime: Date.now() - startup,
  };

  res.json(data);

});

app.listen(3000);

/* Keyboard Logger*/

ioHook.on("keydown", event => {

  console.log(event);

  contadores[event.keycode] = Date.now();

});

ioHook.on("keyup", event => {

  console.log(event);

  if(data[event.keycode] === undefined) {

    data[event.keycode] = { ltime: 0, duration: 0, counter: 0, holds: 0, taps: 0, mouseclick: 0, positionx: [], positiony: [], type: event.type}

  }

  let chrono = Date.now() - contadores[event.keycode];

  data[event.keycode].counter += 1;
  data[event.keycode].duration += chrono;
  data[event.keycode].ltime = Date.now();
  data[event.keycode].holds += (chrono > 250) ? 1 : 0;
  data[event.keycode].taps += (chrono < 100) ? 1 : 0;
  data[event.keycode].mouseclick = 0;
  data[event.keycode].type = event.type;

});

/* Mouse Click Logger */

ioHook.on("mousedown", event => {

  console.log(event);

  contadores[999] = Date.now();

});

ioHook.on("mouseup", event => {

  console.log(event);

  if(data[998] === undefined) {

    data[998] = { ltime: 0, duration: 0, counter: 0, holds: 0, taps: 0, mouseclick: 1, positionx: [], positiony: [], type: event.type}

  }

  let chrono = Date.now() - contadores[999];

  data[998].counter += 1;
  data[998].duration += chrono;
  data[998].ltime = Date.now();
  data[998].holds += (chrono > 250) ? 1 : 0;
  data[998].taps += (chrono < 100) ? 1 : 0;
  data[998].mouseclick = 1;
  data[998].positionx.unshift(event.x);
  data[998].positiony.unshift(event.y);
  data[998].type = event.type;

});

/* Mouse Movement Logger */

ioHook.on("mousemove", event => {

  //console.log(event);

  if(data[1000] === undefined) {

    data[1000] = { ltime: 0, duration: 0, counter: 0, holds: 0, taps: 0, mouseclick: 0, positionx: [], positiony: [], type: event.type}

  }

  data[1000].counter += 1;
  data[1000].duration = 0;
  data[1000].ltime = Date.now();
  data[1000].mouseclick = 0;
  data[1000].positionx.unshift(event.x);
  data[1000].positiony.unshift(event.y);
  data[1000].type = event.type;

});


ioHook.start();

console.log('LOGGING...')
