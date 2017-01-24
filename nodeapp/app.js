'use strict';

var express = require('express');
var app = express();
var port = process.argv[2];

app.get('/', function (req, res) {
  res.send('Hello from Mesos-NodeJs App\n');
});

app.get('/ping', function (req, res) {
  res.send('Pong!\n');
});

app.get('/health', function (req, res) {
  res.status(200).send('OK!\n');
});

app.get('/error', function (req, res) {
  res.status(405).send('Eroor!\n');
});

app.get('/exception', function (req, res) {
  res.status(503).send('Exception!\n');
});

// Start the server
var server = app.listen(port || '8080', function () {
  console.log('App listening on port %s', server.address().port);
  console.log('Press Ctrl+C to quit.');
});
