package gui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

class DiceGui extends Group {
    private static TriangleMesh mesh;
    private final static Image backgroundImage = new Image("images/dice.png");

    static {
        float hw = 70 / 2f;
        float hh = 70 / 2f;
        float hd = 70 / 2f;

        float points[] = {
                hw, hh, hd,
                hw, hh, -hd,
                hw, -hh, hd,
                hw, -hh, -hd,
                -hw, hh, hd,
                -hw, hh, -hd,
                -hw, -hh, hd,
                -hw, -hh, -hd};

        float tex[] = {
                0.25f, 0,
                0.5f, 0,
                0, 0.33f,
                0.25f, 0.33f,
                0.5f,0.33f,
                0.75f, 0.33f,
                1, 0.33f,
                0, 0.66f,
                0.25f, 0.66f,
                0.5f, 0.66f,
                0.75f, 0.66f,
                1, 0.66f,
                0.25f ,1f,
                0.5f,1f};

        int faces[] = {
                0, 10, 2, 5, 1, 9,
                2, 5, 3, 4, 1, 9,
                4, 7, 5, 8, 6, 2,
                6, 2, 5, 8, 7, 3,
                0, 13, 1, 9, 4, 12,
                4, 12, 1, 9, 5, 8,
                2, 1, 6, 0, 3, 4,
                3, 4, 6, 0, 7, 3,
                0, 10, 4, 11, 2, 5,
                2, 5, 4, 11, 6, 6,
                1, 9, 3, 4, 5, 8,
                5, 8, 3, 4, 7, 3};

        mesh = new TriangleMesh();
        mesh.getPoints().addAll(points);
        mesh.getTexCoords().addAll(tex);
        mesh.getFaces().addAll(faces);
    }

    DiceGui() {

        PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setSpecularColor(Color.WHITE);
        redMaterial.setDiffuseMap(backgroundImage);
        MeshView meshView = new MeshView(mesh);
        meshView.setMaterial(redMaterial);
        meshView.setTranslateX(-30);

        meshView.getTransforms().addAll(new Rotate(270,Rotate.X_AXIS));
        this.getChildren().add(meshView);
        // 0 0 0 1
        // 0 90 0 2
        // 0 180 0 3
        // 0 270 0 4
        // 90 0 0 5
        // 270 0 0 6
    }
}
