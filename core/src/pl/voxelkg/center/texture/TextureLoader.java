package pl.voxelkg.center.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;

/**
 * Created by Acer pc on 2015-07-25.
 */
public class TextureLoader {
    private Mesh mesh;
    private Texture texture;

    void load(){
        if (mesh == null) {
            mesh = new Mesh(true, 3, 3,
                    new VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"),
                    new VertexAttribute(VertexAttributes.Usage.ColorPacked, 4, "a_color"),
                    new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, "a_texCoords"));

            mesh.setVertices(new float[] { -0.5f, -0.5f, 0, Color.toFloatBits(255, 0, 0, 255), 0, 1,
                    0.5f, -0.5f, 0, Color.toFloatBits(0, 255, 0, 255), 1, 1,
                    0, 0.5f, 0, Color.toFloatBits(0, 0, 255, 255), 0.5f, 0 });

            mesh.setIndices(new short[] { 0, 1, 2 });

            FileHandle imageFileHandle = Gdx.files.internal("textures/badlogic.jpg");
            texture = new Texture(imageFileHandle);
        }
        FileHandle imageFileHandle = Gdx.files.internal("textures/badlogic.jpg");
        texture = new Texture(imageFileHandle);
    }

}
