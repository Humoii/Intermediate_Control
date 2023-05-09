package ControlWorkJava;

public class Toys {
    private String idToy;
    private String nameToy;
    private Integer quantityToy;
    private Integer dropToy;

    public Toys (){
        this.idToy = null;
        this.nameToy = null;
        this.quantityToy = null;
        this.dropToy = null;
    }

    public void  setIdToy(String idToy){

        this.idToy = idToy;

    }
    public void  setNameToy(String nameToy){

        this.nameToy = nameToy;

    }
    public void  setQuantityToy(Integer quantityToy){

        this.quantityToy = quantityToy;

    }
    public void  setDropToy(Integer dropToy){

        this.dropToy = dropToy;

    }


    public String toStringToys() {

        return "Toys{" +
                "idToy=" + idToy +
                ", nameToy='" + nameToy + '\'' +
                ", quantityToy=" + quantityToy +
                ", dropToy=" + dropToy +
                '}';

    }

    public String getIdToy() {

        return idToy;

    }

    public String getNameToy() {

        return nameToy;

    }
    public Integer getQuantityToy() {

        return quantityToy;

    }
    public Integer getDropToy() {

        return dropToy;

    }

}
