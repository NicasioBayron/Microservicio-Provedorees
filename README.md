# Proveedores Microservicio

## POST /proveedores
Crea un proveedor nuevo.

### Body JSON
```json
{
  "nombreEmpresa": "Proveedor X",
  "contactoNombre": "Carlos Pérez",
  "telefono": "123456789",
  "categoriaInsumo": "Instrumentos"
}
```

## PUT /proveedores/{idProveedor}
Actualiza un proveedor existente.

### Body JSON
```json
{
  "nombreEmpresa": "Proveedor X Actualizado",
  "contactoNombre": "Carlos Pérez",
  "telefono": "987654321",
  "categoriaInsumo": "Instrumentos"
}
```
