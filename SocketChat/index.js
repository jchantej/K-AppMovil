var express = require('express');
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function (req, res) {
    res.sendFile(__dirname + '/index.html');
});

/*Se especifica el socket par el chat*/
io.on('connection', function (socket) {
    console.log('Un usuario conectado ID :'+ socket.id);
    socket.on('enviar mensaje', function (msg) {
        //Captura los memnsajes que llegan al servidor y los muestra en consola
        console.log('message: ' + msg);
        io.sockets.emit('nuevo mensaje', msg)
    });
});


http.listen(3001, function () {
    console.log('listening on *:3001');
});

