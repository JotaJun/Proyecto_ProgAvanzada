package org.example.JavaFX;
import javafx.scene.paint.*;

public enum Colores {
    ROSA_PASTEL("#FFD1DC"),
    AZUL_PASTEL("#AEC6CF"),
    VERDE_MENTA("#AAF0D1"),
    CARAMEL("#BC8F8F"),
    AMARILLO_SUAVE("#FFFACD"),
    LILA_CLARO("#D8BFD8"),
    CELESTE_PASTEL("#B0E0E6"),
    GRIS("grey"),
    NEGRO("black");
    private final Color color;
    private final String code;


    Colores(String hex) {
        this.code = hex;
        this.color = Color.web(hex);
    }

    public String getCode() {
        return code;
    }
    public Color getColor() {
        return color;
    }

}
