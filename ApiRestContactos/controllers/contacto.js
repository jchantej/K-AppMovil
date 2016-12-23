var mongoose = require('mongoose');
var Contacto = mongoose.model('Contacto');

//GET - Return all registers
exports.findAll = function (req, res) {
    Contacto.find(function (err, contactos) {
        if (err) res.send(500, err.message);
        console.log('GET /contactos')
        res.status(200).jsonp(contactos);
    });
};

//GET - Return a register with specified ID
exports.findById = function (req, res) {
    Contacto.findById(req.params.id, function (err, contacto) {
        if (err) return res.send(500, err.message);
        console.log('GET /contactos/' + req.params.id);
        res.status(200).jsonp(contacto);
    });
};

//POST - Insert a new register
exports.add = function (req, res) {
    console.log('POST');
    console.log(req.body);
    var contacto = new Contacto({
        nombre: req.body.nombre,
        telefono: req.body.telefono,
        correo: req.body.correo,
        genero: req.body.genero
    });
    contacto.save(function (err, contacto) {
        if (err) return res.send(500, err.message);
        res.status(200).jsonp(contacto);
    });
};

//PUT - Update a register already exists
exports.update = function (req, res) {
    Contacto.findById(req.params.id, function (err, contacto) {
        contacto.nombre = req.body.nombre;
        contacto.telefono = req.body.telefono;
        contacto.correo = req.body.correo;
        contacto.genero = req.body.genero;

        contacto.save(function (err) {
            if (err) return res.send(500, err.message);
            res.status(200).jsonp(contacto);   
        });
    });
};

//DELETE - Delete a register with specified ID
exports.delete = function (req, res) {
    Contacto.findById(req.params.id, function (err, contacto) {
        contacto.remove(function (err) {
            if (err) return res.send(500, err.message);
            res.json({ message: 'Datos Borrados Correctamente' });
        });
    });
};