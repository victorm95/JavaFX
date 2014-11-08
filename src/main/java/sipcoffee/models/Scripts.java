package sipcoffee.models;

import java.util.Date;

public class Scripts extends Thread {

    private Usuario usuario;
    private Departamento departamento;
    private String listDepartamentos[];
    private String municipios[];

    @Override
    public void run() {

        Conexion.init();

        this.usuario = new Usuario();
        if (this.usuario.isEmpty()) {
            Rol rolusuario = new Rol("Admin");
            rolusuario.save();

            usuario.setNombre("usuario");
            usuario.setUsuario("root");
            usuario.setClave("root");
            usuario.setExpedicionDocumento(new Date());
            usuario.setCedula(0);
            usuario.setDireccion("----");
            usuario.setTelefono(0);
            usuario.setActivo(true);
            usuario.setRol(rolusuario);

            if (usuario.save())
                System.out.println("User usuario created correctly");
        }

        this.departamento = new Departamento();
        if (this.departamento.isEmpty()) {
            this.listDepartamentos = new String[]{"Antioquia", "Caldas",
                    "Risaralda", "Bogota", "Atlantico", "Bolivar", "Cordoba",
                    "San Andres y Providencia", "Sucre", "Arauca", "Boyaca",
                    "Casanare", "Amazonas", "Caqueta", "Cauca", "Choco",
                    "Guainia", "Guaviare", "Narino", "Putumayo", "Quindio",
                    "Valle del Cauca", "Vaupes"};

            for (String d : this.listDepartamentos) {
                Departamento temp = new Departamento(d);
                if (temp.save()) {
                    System.out.println("[save]: " + d);

                    if (d.equals("Antioquia")) {
                        new Municipio("Andes", temp).save();
                        new Municipio("Medellin", temp).save();
                    }
                }
            }
        }

        //System.out.println("[Parcelas]: " + new ParcelaCtrl().all());
        System.out.println(new Parcela().getData());

    }
}
