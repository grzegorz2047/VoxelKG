package pl.voxelkg.center.gameobject.block;

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
public class Block {
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

    public double distance(Vector3 loc){
        float x1 = this.location.x;//origin loc
        float x2 = loc.x;//second loc

        float y1 = this.location.y;
        float y2 = loc.y;

        float z1 = this.location.z;
        float z2 = loc.z;

        double d = Math.sqrt( Math.pow(x2-x1,2)+Math.pow(y2-y1,2)+Math.pow(z2-z1,2));
        return d;
    }

    private static FileHandle imageFileHandle = Gdx.files.internal("textures/badlogic.jpg");
    private static Texture t = new Texture(imageFileHandle);


    public Block(float x, float y, float z) {
        this.size = 1;
        this.center = new Vector3(x/2,y/2, z/2);
        this.location = new Vector3(x,y,z);
        this.model = new Model();

        generateMesh();
    }
    public void generateMesh(){
        if(this.location.y>64){
            instance = new ModelInstance(model);
            return;
        }
        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(size, size, size,
                new Material(), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instance = new ModelInstance(model);
        instance.transform.setToTranslation(this.location);
        instance.materials.first().set(TextureAttribute.createDiffuse(t));

    }

    public void update(final Vector3 position) {
        center.set(position);
    }

}
