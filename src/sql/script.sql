create database BDFiscalia;

use BDFiscalia;

create table Departamento(
  id_dep int auto_increment primary key,
  nom_dep varchar(100)
);

create table Denuncia(
  id_denuncia int auto_increment primary key,
  id_dep int,
  nom_ape varchar(120),
  dni char(8),
  correo varchar(100),
  telefono char(9),
  centro_laboral varchar(100),
  direccion varchar(120),
  denuncia varchar(1000),
  foreign key(id_dep) references Departamento(id_dep)
);

create procedure sp_listarTodosDenuncias()
begin
  SELECT * FROM Departamento d inner join denuncia e on d.id_dep = e.id_dep;
end;

create procedure sp_registrarDenuncias
(
_id_dep int,
_nom_ape varchar(120),
_dni char(8),
_correo varchar(100),
_telefono char(9),
_centro_laboral varchar(100),
_direccion varchar(120),
_denuncia varchar(1000)
)
begin
  insert into Denuncia(id_dep,nom_ape,dni,correo,telefono,centro_laboral,direccion,denuncia) 
  values(_id_dep,_nom_ape,_dni,_correo,_telefono,_centro_laboral,_direccion,_denuncia);
end;

create procedure sp_editarDenuncias
(
_id_dep int,
_nom_ape varchar(120),
_dni char(8),
_correo varchar(100),
_telefono char(9),
_centro_laboral varchar(100),
_direccion varchar(120),
_denuncia varchar(1000),
_id_denuncia int
)
begin
  update Denuncia set id_dep=_id_dep,nom_ape=_nom_ape,dni=_dni,correo=_correo,
  telefono=_telefono,centro_laboral=_centro_laboral,direccion=_direccion,
  denuncia=_denuncia where id_denuncia=_id_denuncia;
end;

create procedure sp_eliminarDenuncias
(
_id_denuncia int
)
begin
  delete from Denuncia 
  where id_denuncia=_id_denuncia;
end;


create procedure sp_reporteIncidencias()
begin
  SELECT d.nom_dep , count(1)
  FROM Departamento d inner join denuncia e 
  on d.id_dep = e.id_dep
  group by d.nom_dep;
end;

insert into Departamento(nom_dep) values('Lima');
insert into Departamento(nom_dep) values('Huanuco');
insert into Departamento(nom_dep) values('Junin');


