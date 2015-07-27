package pl.voxelkg.center;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import pl.voxelkg.center.chunk.Chunk;
import pl.voxelkg.center.gameobject.block.Block;

public class VoxelKG extends ApplicationAdapter {

	private Environment environment;
	private PerspectiveCamera cam;
	private CameraInputController camController;
	private ModelBatch modelBatch;
	private Chunk chunk;
	private FPSLogger fps;
	private AssetManager assets;
	public ModelInstance space;

	@Override
	public void create () {
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		this.modelBatch = new ModelBatch();
		this.chunk = new Chunk();
		for(int x = -16; x < 16	; x+=4){
			for(int z = -16; z < 16; z+=4){
				this.chunk.generate(x,z);
			}
		}

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(15f, 5f, 15f);
		cam.near = 1f;
		cam.far = 500f;
		cam.update();

		camController = new CameraInputController(cam);

		Gdx.input.setInputProcessor(camController);

		fps = new FPSLogger();

		assets = new AssetManager();
		assets.load("objects/space/spacesphere.obj", Model.class);
	}

	private Texture t;

	boolean  n = false;
	@Override
	public void render () {
		if(!n && assets.update()){
			space = new ModelInstance(assets.get("objects/space/spacesphere.obj", Model.class));
			n=true;
		}


		camController.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


		int i = 0;
		this.modelBatch.begin(cam);
		for(Block block : this.chunk.getBlocks()){
			if(isVisible(cam, block.getInstance()) && block.distance(cam.position) < 16 ){
				i++;
				this.modelBatch.render(block.getInstance(), environment);
			}

		}
		if (space != null){
			this.modelBatch.render(space);
		}
		this.modelBatch.end();
		Gdx.graphics.setTitle("VoxelKG " + Gdx.graphics.getFramesPerSecond()+" obj: "+i);
	}
	private Vector3 position = new Vector3();

	protected boolean isVisible(final Camera cam, final ModelInstance instance) {
		instance.transform.getTranslation(position);

		return cam.frustum.pointInFrustum(position);
	}
	@Override
	public void dispose() {

		for(Block block : this.chunk.getBlocks()) {
			this.modelBatch.dispose();
			block.getModel().dispose();
		}
		assets.dispose();
	}



}
