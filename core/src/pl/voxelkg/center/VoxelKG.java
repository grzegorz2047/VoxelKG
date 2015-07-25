package pl.voxelkg.center;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import pl.voxelkg.center.chunk.Chunk;
import pl.voxelkg.center.gameobject.Box;

public class VoxelKG extends ApplicationAdapter {

	public Environment environment;
	public PerspectiveCamera cam;
	public CameraInputController camController;
	private ModelBatch modelBatch;
	private Chunk chunk;
	FPSLogger fps;

	@Override
	public void create () {
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		this.modelBatch = new ModelBatch();
		this.chunk = new Chunk();
		for(int x = -4; x < 4; x++){
			for(int z = -4; z < 4; z++){
				this.chunk.generate(x,z);
			}
		}

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(15f, 5f, 15f);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);

		fps = new FPSLogger();
	}

	Texture t;
	@Override
	public void render () {
		Gdx.graphics.setTitle("VoxelKG " + Gdx.graphics.getFramesPerSecond());
		camController.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		FileHandle imageFileHandle = Gdx.files.internal("textures/badlogic.jpg");
		t = new Texture(imageFileHandle);

		this.modelBatch.begin(cam);
		for(Box box : this.chunk.getCubes()) {
			if (isVisible(cam, box.getInstance())) {
				box.getInstance().materials.first().set(TextureAttribute.createDiffuse(t));
				this.modelBatch.render(box.getInstance(), environment);
			}

		}
		this.modelBatch.end();
	}
	private Vector3 position = new Vector3();
	protected boolean isVisible(final Camera cam, final ModelInstance instance) {
		instance.transform.getTranslation(position);
		return cam.frustum.pointInFrustum(position);
	}
	@Override
	public void dispose() {
		for(Box box : this.chunk.getCubes()) {
			this.modelBatch.dispose();
			box.getModel().dispose();
		}
	}



}
