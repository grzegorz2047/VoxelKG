package pl.voxelkg.center.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Acer pc on 2015-07-25.
 */
public class Box {
    private final Vector3 location;
    private float size = 1;
    private final Vector3 center;

    private Model model;
    private ModelInstance instance;

    public float getSize() {
        return size;
    }

    public Vector3 getCenter() {
        return center;
    }


    public Model getModel() {
        return model;
    }

    public ModelInstance getInstance() {
        return instance;
    }

    public Box(float x, float y, float z) {
        this.size = 1;
        this.center = new Vector3(x/2,y/2, z/2);
        this.location = new Vector3(x,y,z);
        this.model = new Model();
        generateMesh();
    }
    private void generateMesh(){
        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(size, size, size,
                new Material(), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instance = new ModelInstance(model);
        instance.transform.setToTranslation(this.location);
    }

    public void update(final Vector3 position) {
        center.set(position);
    }

}
