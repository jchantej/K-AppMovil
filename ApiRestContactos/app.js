var express = require('express');
var bodyParser = require('body-parser');
var mongoose = require('mongoose');
var methodOverride = require("method-override");
var app = express();

// Connection to DB
mongoose.connect('mongodb://localhost/contactos', function(err, res) {
 if(err) throw err;
 console.log('Connected to Database');
});

// Middlewares
app.use(bodyParser.urlencoded({ extended: false })); 
app.use(bodyParser.json()); 
app.use(methodOverride());

// Import Models and Controllers
var models = require('./models/contacto')(app, mongoose);
var ContactoCtrl = require('./controllers/contacto');

var router = express.Router();

// Index - Route
router.get('/', function(req, res) { 
 res.send("Servidor Corriendo para porbar API REST");
});

app.use(router);

// API routes
var api = express.Router();

api.route('/contactos') 
 .get(ContactoCtrl.findAll)
 .post(ContactoCtrl.add);

api.route('/contactos/:id') 
 .get(ContactoCtrl.findById)
 .put(ContactoCtrl.update)
 .delete(ContactoCtrl.delete);

app.use('/api', api);


// Start server
app.listen(3000, function() {
 console.log("Node server running on http://localhost:3000");
});