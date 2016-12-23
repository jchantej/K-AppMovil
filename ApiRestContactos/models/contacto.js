var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var contactoSchema = new Schema({ 
 nombre: { type: String },
 telefono:{type: Number},
 correo: { type: String },
 genero: { type: String, enum: ['masculino', 'femenino'] }
});

module.exports = mongoose.model('Contacto', contactoSchema);